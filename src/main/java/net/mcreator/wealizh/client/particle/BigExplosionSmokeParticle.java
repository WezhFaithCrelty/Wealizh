package net.mcreator.wealizh.client.particle;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

public class BigExplosionSmokeParticle extends TextureSheetParticle {
	public static BigExplosionSmokeParticleProvider provider(SpriteSet spriteSet) {
		return new BigExplosionSmokeParticleProvider(spriteSet);
	}

	public static class BigExplosionSmokeParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public BigExplosionSmokeParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new BigExplosionSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected BigExplosionSmokeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(1f, 1f);
		this.quadSize *= 10f+this.random.nextDouble()*1.50-0.75;
		this.lifetime = (int) Math.max(1, 400 + (this.random.nextInt(120) - 60));
		this.gravity = 0f;
		this.hasPhysics = true;
		this.xd = vx * 1;
		this.yd = vy * 1;
		this.zd = vz * 1;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public void tick() {
		super.tick();
		float p = (float)this.age / (float)this.lifetime;
		if (p < 0.05f) {
	        float t = p / 0.05f;
	        this.rCol = 1.0f;
	        this.gCol = 1.0f - t * 0.1f;
	        this.bCol = 1.0f - t * 0.7f;
	    } else if (p < 0.20f) {
	        float t = (p - 0.05f) / 0.15f;
	        this.rCol = 1.0f;
	        this.gCol = 0.9f - t * 0.2f;
	        this.bCol = 0.3f - t * 0.2f;
	    } else if (p < 0.35f) {
	        float t = (p - 0.20f) / 0.15f;
	        this.rCol = 1.0f;
	        this.gCol = 0.7f - t * 0.4f;
	        this.bCol = 0.1f - t * 0.1f;
	    } else if (p < 0.50f) {
	        float t = (p - 0.35f) / 0.15f;
	        float easeT = t * t * (3.0f - 2.0f * t);
	        this.rCol = 1.0f - easeT * 0.9f;
	        this.gCol = 0.3f - easeT * 0.2f;
	        this.bCol = easeT * 0.1f;
	    } else {
	        this.rCol = 0.1f;
	        this.gCol = 0.1f;
	        this.bCol = 0.1f;
	    }if(0.9<p)this.setSprite(this.spriteSet.get((int)(16.0/(this.lifetime*0.1)*(this.age-this.lifetime*0.9))+1,16));
	}
}