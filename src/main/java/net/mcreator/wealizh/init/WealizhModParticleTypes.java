/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.wealizh.WealizhMod;

public class WealizhModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, WealizhMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLUE_LIGHT = REGISTRY.register("blue_light", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EXPLOSION_SMOKE = REGISTRY.register("explosion_smoke", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPARK = REGISTRY.register("spark", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BIG_EXPLOSION_SMOKE = REGISTRY.register("big_explosion_smoke", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DUST_PARTICLES = REGISTRY.register("dust_particles", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HUGE_EXPLOSION_SMOKE = REGISTRY.register("huge_explosion_smoke", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MUSHROOM_CLOUD = REGISTRY.register("mushroom_cloud", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HUGE_SMOKE = REGISTRY.register("huge_smoke", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GIANT_CLOUD_OF_SMOKE = REGISTRY.register("giant_cloud_of_smoke", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HIGH_TEMPERATURE_SMOKE = REGISTRY.register("high_temperature_smoke", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HUGE_PERMANENCE_SMOKE = REGISTRY.register("huge_permanence_smoke", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DESOLATE_DUST = REGISTRY.register("desolate_dust", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MUSHROOM_CLOUD_STEM = REGISTRY.register("mushroom_cloud_stem", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SMOKE = REGISTRY.register("smoke", () -> new SimpleParticleType(false));
}