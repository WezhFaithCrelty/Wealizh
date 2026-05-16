package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class RadiationFantasyFogXianShiYouXiNeiDieJiaCengProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return 4000 <= entity.getPersistentData().getDoubleOr("Rad", 0) && (entity instanceof Player _plr1 && _plr1.gameMode() == GameType.SURVIVAL || entity instanceof Player _plr2 && _plr2.gameMode() == GameType.ADVENTURE);
	}
}