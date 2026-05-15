package net.mcreator.wealizh.client.particle;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

public class MushroomCloudParticle extends TextureSheetParticle {
	public static MushroomCloudParticleProvider provider(SpriteSet spriteSet) {
		return new MushroomCloudParticleProvider(spriteSet);
	}

	public static class MushroomCloudParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public MushroomCloudParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new MushroomCloudParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected MushroomCloudParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(1f, 1f);
		this.quadSize *= 48f;
		this.lifetime = 3000;
		this.gravity = 0f;
		this.hasPhysics = false;
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
	    if (p < 0.15f) {
	        float t = p / 0.15f;
	        this.rCol = 1.0f;
	        this.gCol = 1.0f;
	        this.bCol = 1.0f - t * 1.0f;
	        this.rCol = 1.0f;
	        this.gCol = 1.0f - t * 0.157f;
	        this.bCol = 1.0f - t;
	    } else if (p < 0.30f) {
	        float t = (p - 0.15f) / 0.15f;
	        this.rCol = 1.0f - t * 0.749f;
	        this.gCol = 0.843f - t * 0.655f;
	        this.bCol = 0.0f + t * 0.157f;
	    } else {
	        this.rCol = 0.251f;
	        this.gCol = 0.188f;
	        this.bCol = 0.157f;
	    }if(0.9<p)this.setSprite(this.spriteSet.get((int)(16.0/(this.lifetime*0.1)*(this.age-this.lifetime*0.9))+1,16));
	}
}