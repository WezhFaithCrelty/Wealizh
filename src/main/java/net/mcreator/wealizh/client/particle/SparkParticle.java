package net.mcreator.wealizh.client.particle;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

public class SparkParticle extends TextureSheetParticle {
	public static SparkParticleProvider provider(SpriteSet spriteSet) {
		return new SparkParticleProvider(spriteSet);
	}

	public static class SparkParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public SparkParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new SparkParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;
	private float angularVelocity;
	private float angularAcceleration;

	protected SparkParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.2f, 0.2f);
		this.quadSize *= 0.2f;
		this.lifetime = (int) Math.max(1, 60 + (this.random.nextInt(36) - 18));
		this.gravity = 1f;
		this.hasPhysics = true;
		this.xd = vx * 0.3;
		this.yd = vy * 0.3;
		this.zd = vz * 0.3;
		this.angularVelocity = 0.314f;
		this.angularAcceleration = 0f;
		this.pickSprite(spriteSet);
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
		this.oRoll = this.roll;
		this.roll += this.angularVelocity;
		this.angularVelocity += this.angularAcceleration;
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
	    } else {
	        this.rCol = 1.0f;
	        this.gCol = 0.3f;
	        this.bCol = 0;
	    }
	}
}