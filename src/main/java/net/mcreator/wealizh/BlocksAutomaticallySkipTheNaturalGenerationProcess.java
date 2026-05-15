package net.mcreator.wealizh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.nio.file.Path;

// 添加导入
import net.mcreator.wealizh.procedures.TemporaryBlockReplacementSPProcedure;

@EventBusSubscriber(modid = WealizhMod.MODID)
public class BlocksAutomaticallySkipTheNaturalGenerationProcess {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BlocksAutomaticallySkipTheNaturalGenerationProcess.class);
    
    // 每个存档独立的保存文件
    private static File SAVE_FILE = null;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    // 已处理的区块（持久化），键是维度ID
    private static final Map<String, Set<Long>> PROCESSED_CHUNKS = new ConcurrentHashMap<>();
    
    // 待处理的区块队列
    private static final Queue<ChunkTask> CHUNK_QUEUE = new ConcurrentLinkedQueue<>();
    
    // 玩家扫描状态，键是玩家UUID
    private static final Map<UUID, PlayerScanData> PLAYER_SCAN_DATA = new ConcurrentHashMap<>();
    
    // 统计
    private static final AtomicInteger totalBlocksActivated = new AtomicInteger(0);
    private static final AtomicInteger totalChunksProcessed = new AtomicInteger(0);
    
    // 配置
    private static final int MAX_CHUNKS_PER_TICK = 1;
    private static final int MAX_SCAN_RADIUS = 32;
    private static final int MAX_QUEUE_SIZE = 1000;
    
    // 反射方法缓存
    private static final Map<Block, Boolean> BLOCK_HAS_TICK_METHOD = new ConcurrentHashMap<>();
    
    // 服务器实例
    private static net.minecraft.server.MinecraftServer SERVER_INSTANCE = null;
    
    // 是否在集成服务器（单人游戏）
    private static boolean IS_SINGLE_PLAYER = false;
    
    // 初始化标志
    private static boolean IS_INITIALIZED = false;
    
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("[Wealizh] 区块处理系统初始化");
        
        SERVER_INSTANCE = event.getServer();
        IS_SINGLE_PLAYER = !event.getServer().isDedicatedServer();
        IS_INITIALIZED = true;
        
        Path serverDirectoryPath = event.getServer().getServerDirectory();
        File serverDirectoryFile = serverDirectoryPath.toFile();
        
        String levelName = event.getServer().getWorldData().getLevelName();
        
        File saveDir;
        if (IS_SINGLE_PLAYER) {
            File worldFolder = new File(serverDirectoryFile, "saves/" + levelName);
            saveDir = new File(worldFolder, "wealizh_data");
        } else {
            File worldFolder = new File(serverDirectoryFile, levelName);
            saveDir = new File(worldFolder, "wealizh_data");
        }
        
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        
        SAVE_FILE = new File(saveDir, "processed_chunks.json");
        
        loadProcessedChunks();
    }
    
    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        saveProcessedChunks();
        
        SERVER_INSTANCE = null;
        IS_SINGLE_PLAYER = false;
        IS_INITIALIZED = false;
    }
    
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }
        
        if (level.isClientSide()) {
            return;
        }
        
        if (!IS_INITIALIZED) {
            return;
        }
        
        String dimensionId = level.dimension().location().toString();
        
        if (CHUNK_QUEUE.size() < MAX_QUEUE_SIZE) {
            for (ServerPlayer player : level.players()) {
                if (player.level() == level) {
                    collectChunksForPlayer(player, level, dimensionId);
                }
            }
        }
        
        processChunkQueue(level, dimensionId);
    }
    
    private static void collectChunksForPlayer(ServerPlayer player, ServerLevel level, String dimensionId) {
        UUID playerId = player.getUUID();
        PlayerScanData data = PLAYER_SCAN_DATA.computeIfAbsent(playerId, k -> new PlayerScanData());
        
        int playerChunkX = player.chunkPosition().x;
        int playerChunkZ = player.chunkPosition().z;
        
        int simulationDistance = MAX_SCAN_RADIUS;
        if (SERVER_INSTANCE != null) {
            simulationDistance = Math.min(SERVER_INSTANCE.getPlayerList().getSimulationDistance(), MAX_SCAN_RADIUS);
        }
        
        data.updatePosition(playerChunkX, playerChunkZ, simulationDistance, dimensionId);
        
        Set<Long> dimProcessed = PROCESSED_CHUNKS.computeIfAbsent(dimensionId, k -> ConcurrentHashMap.newKeySet());
        
        for (int radius = 0; radius <= simulationDistance; radius++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (Math.abs(dx) != radius && Math.abs(dz) != radius) {
                        continue;
                    }
                    
                    int chunkX = playerChunkX + dx;
                    int chunkZ = playerChunkZ + dz;
                    
                    if (data.hasScannedChunk(chunkX, chunkZ, dimensionId)) {
                        continue;
                    }
                    
                    long chunkKey = getChunkKey(chunkX, chunkZ);
                    
                    if (dimProcessed.contains(chunkKey)) {
                        data.markChunkScanned(chunkX, chunkZ, dimensionId);
                        continue;
                    }
                    
                    if (!level.hasChunk(chunkX, chunkZ)) {
                        continue;
                    }
                    
                    boolean inQueue = CHUNK_QUEUE.stream().anyMatch(task -> 
                        task.dimensionId.equals(dimensionId) && 
                        task.chunkX == chunkX && task.chunkZ == chunkZ);
                    
                    if (!inQueue && CHUNK_QUEUE.size() < MAX_QUEUE_SIZE) {
                        CHUNK_QUEUE.offer(new ChunkTask(dimensionId, chunkX, chunkZ, level));
                        data.markChunkScanned(chunkX, chunkZ, dimensionId);
                    }
                    
                    if (CHUNK_QUEUE.size() >= MAX_QUEUE_SIZE) {
                        break;
                    }
                }
                
                if (CHUNK_QUEUE.size() >= MAX_QUEUE_SIZE) {
                    break;
                }
            }
            
            if (CHUNK_QUEUE.size() >= MAX_QUEUE_SIZE) {
                break;
            }
        }
        
        if (data.lastChunkX != playerChunkX || data.lastChunkZ != playerChunkZ) {
            data.clearScannedChunks(dimensionId);
            data.updatePosition(playerChunkX, playerChunkZ, simulationDistance, dimensionId);
        }
    }
    
    private static void processChunkQueue(ServerLevel level, String currentDimensionId) {
        int processed = 0;
        
        while (!CHUNK_QUEUE.isEmpty() && processed < MAX_CHUNKS_PER_TICK) {
            ChunkTask task = CHUNK_QUEUE.peek();
            if (task == null) {
                break;
            }
            
            if (!task.dimensionId.equals(currentDimensionId)) {
                break;
            }
            
            task = CHUNK_QUEUE.poll();
            processChunk(task);
            processed++;
        }
    }
    
    private static void processChunk(ChunkTask task) {
        long chunkKey = getChunkKey(task.chunkX, task.chunkZ);
        
        try {
            ServerLevel level = task.level;
            if (level == null || level.isClientSide()) {
                return;
            }
            
            if (!level.dimension().location().toString().equals(task.dimensionId)) {
                return;
            }
            
            LevelChunk chunk = level.getChunk(task.chunkX, task.chunkZ);
            if (chunk == null) {
                return;
            }
            
            int activatedBlocks = 0;
            int startX = task.chunkX << 4;
            int startZ = task.chunkZ << 4;
            int minY = level.getMinY();
            int maxY = level.getMaxY();
            
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = minY; y <= maxY; y++) {
                        BlockPos pos = new BlockPos(startX + x, y, startZ + z);
                        BlockState state = level.getBlockState(pos);
                        
                        if (state.isAir()) {
                            continue;
                        }
                        
                        Block block = state.getBlock();
                        
                        if (block instanceof FallingBlock) {
                            continue;
                        }
                        
                        if (hasScheduledTickProcess(block, state)) {
                            if (replaceBlockWithNBT(level, pos, state)) {
                                activatedBlocks++;
                            }
                        }
                    }
                }
            }
            
            Set<Long> dimProcessed = PROCESSED_CHUNKS.computeIfAbsent(task.dimensionId, k -> ConcurrentHashMap.newKeySet());
            dimProcessed.add(chunkKey);
            
            totalChunksProcessed.incrementAndGet();
            totalBlocksActivated.addAndGet(activatedBlocks);
            
            saveProcessedChunks();
            
        } catch (Exception e) {
        }
    }
    
    private static boolean hasScheduledTickProcess(Block block, BlockState state) {
        Boolean cached = BLOCK_HAS_TICK_METHOD.get(block);
        if (cached != null) {
            return cached;
        }
        
        boolean hasTickMethod = false;
        try {
            Class<?> blockClass = block.getClass();
            
            Method tickMethod = findMethod(blockClass, "tick", 
                new Class<?>[] {BlockState.class, ServerLevel.class, BlockPos.class, net.minecraft.util.RandomSource.class});
            
            if (tickMethod == null) {
                tickMethod = findMethod(blockClass, "tick", 
                    new Class<?>[] {BlockState.class, ServerLevel.class, BlockPos.class, java.util.Random.class});
            }
            
            if (tickMethod == null) {
                Method onPlaceMethod = findMethod(blockClass, "onPlace", 
                    new Class<?>[] {BlockState.class, net.minecraft.world.level.Level.class, BlockPos.class, 
                                   BlockState.class, boolean.class});
                
                hasTickMethod = (tickMethod != null) || (onPlaceMethod != null && 
                    onPlaceMethod.getDeclaringClass() != Block.class);
            } else {
                hasTickMethod = true;
            }
            
        } catch (Exception e) {
        }
        
        BLOCK_HAS_TICK_METHOD.put(block, hasTickMethod);
        return hasTickMethod;
    }
    
    private static Method findMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && superClass != Object.class) {
                return findMethod(superClass, methodName, parameterTypes);
            }
            return null;
        }
    }
    
    // 修复的方块替换方法
    private static boolean replaceBlockWithNBT(ServerLevel level, BlockPos pos, BlockState originalState) {
        try {
        	TemporaryBlockReplacementSPProcedure.execute(
                level, 
                pos.getX(), 
                pos.getY(), 
                pos.getZ(), 
                originalState
            );
            return true;
        } catch (Exception e) {
        	return false;
        }
    }
    
    private static long getChunkKey(int chunkX, int chunkZ) {
        return ((long) chunkX << 32) | (chunkZ & 0xFFFFFFFFL);
    }
    
    private static synchronized void saveProcessedChunks() {
        if (!IS_INITIALIZED) {
            return;
        }
        
        if (SAVE_FILE == null) {
            return;
        }
        
        try {
            File parentDir = SAVE_FILE.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            
            Map<String, Object> saveData = new HashMap<>();
            
            saveData.put("worldName", SERVER_INSTANCE != null ? SERVER_INSTANCE.getWorldData().getLevelName() : "unknown");
            saveData.put("saveTime", System.currentTimeMillis());
            saveData.put("saveVersion", 12);
            saveData.put("totalChunksProcessed", totalChunksProcessed.get());
            saveData.put("totalBlocksActivated", totalBlocksActivated.get());
            
            Map<String, List<String>> serializable = new HashMap<>();
            for (Map.Entry<String, Set<Long>> entry : PROCESSED_CHUNKS.entrySet()) {
                List<String> chunkStrings = entry.getValue().stream()
                    .map(chunkKey -> {
                        int x = (int)(chunkKey >> 32);
                        int z = (int)(chunkKey & 0xFFFFFFFFL);
                        return x + "," + z;
                    })
                    .sorted()
                    .collect(Collectors.toList());
                serializable.put(entry.getKey(), chunkStrings);
            }
            saveData.put("processedChunks", serializable);
            
            File tempFile = new File(SAVE_FILE.getParentFile(), SAVE_FILE.getName() + ".tmp");
            
            try (FileWriter writer = new FileWriter(tempFile)) {
                GSON.toJson(saveData, writer);
            }
            
            if (tempFile.exists() && tempFile.length() > 0) {
                if (SAVE_FILE.exists()) {
                    SAVE_FILE.delete();
                }
                tempFile.renameTo(SAVE_FILE);
            }
            
        } catch (Exception e) {
        }
    }
    
    private static synchronized void loadProcessedChunks() {
        if (SAVE_FILE == null) {
            return;
        }
        
        if (!SAVE_FILE.exists()) {
            return;
        }
        
        try (FileReader reader = new FileReader(SAVE_FILE)) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> saveData = GSON.fromJson(reader, type);
            
            if (saveData != null) {
                Number version = (Number) saveData.get("saveVersion");
                if (version != null && version.intValue() >= 2) {
                }
                
                Number chunks = (Number) saveData.get("totalChunksProcessed");
                Number blocks = (Number) saveData.get("totalBlocksActivated");
                if (chunks != null) {
                    totalChunksProcessed.set(chunks.intValue());
                }
                if (blocks != null) {
                    totalBlocksActivated.set(blocks.intValue());
                }
                
                @SuppressWarnings("unchecked")
                Map<String, List<String>> serializable = (Map<String, List<String>>) saveData.get("processedChunks");
                
                if (serializable != null) {
                    PROCESSED_CHUNKS.clear();
                    
                    for (Map.Entry<String, List<String>> entry : serializable.entrySet()) {
                        Set<Long> chunkKeys = entry.getValue().stream()
                            .map(str -> {
                                try {
                                    String[] parts = str.split(",");
                                    int x = Integer.parseInt(parts[0]);
                                    int z = Integer.parseInt(parts[1]);
                                    return getChunkKey(x, z);
                                } catch (Exception e) {
                                    return 0L;
                                }
                            })
                            .filter(key -> key != 0L)
                            .collect(Collectors.toSet());
                        
                        PROCESSED_CHUNKS.put(entry.getKey(), new HashSet<>(chunkKeys));
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    
    private static class PlayerScanData {
        private int lastChunkX = 0;
        private int lastChunkZ = 0;
        private int simulationDistance = 8;
        private String lastDimension = "";
        private final Map<String, Set<Long>> scannedChunksByDimension = new ConcurrentHashMap<>();
        
        public void updatePosition(int chunkX, int chunkZ, int simulationDistance, String dimensionId) {
            this.lastChunkX = chunkX;
            this.lastChunkZ = chunkZ;
            this.simulationDistance = simulationDistance;
            this.lastDimension = dimensionId;
        }
        
        public boolean hasScannedChunk(int chunkX, int chunkZ, String dimensionId) {
            Set<Long> scannedChunks = scannedChunksByDimension.computeIfAbsent(dimensionId, k -> new HashSet<>());
            return scannedChunks.contains(getChunkKey(chunkX, chunkZ));
        }
        
        public void markChunkScanned(int chunkX, int chunkZ, String dimensionId) {
            Set<Long> scannedChunks = scannedChunksByDimension.computeIfAbsent(dimensionId, k -> new HashSet<>());
            scannedChunks.add(getChunkKey(chunkX, chunkZ));
        }
        
        public void clearScannedChunks(String dimensionId) {
            scannedChunksByDimension.remove(dimensionId);
        }
    }
    
    private static class ChunkTask {
        final String dimensionId;
        final int chunkX;
        final int chunkZ;
        final ServerLevel level;
        
        ChunkTask(String dimensionId, int chunkX, int chunkZ, ServerLevel level) {
            this.dimensionId = dimensionId;
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
            this.level = level;
        }
    }
}