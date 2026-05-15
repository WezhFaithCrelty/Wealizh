package net.mcreator.wealizh.client.particle;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

public class SmokeParticle extends TextureSheetParticle {
	public static SmokeParticleProvider provider(SpriteSet spriteSet) {
		return new SmokeParticleProvider(spriteSet);
	}

	public static class SmokeParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public SmokeParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new SmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected SmokeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.2f, 0.2f);
		this.lifetime=(int)Math.max(1,60+(this.random.nextInt(40)-20));
		this.quadSize*=4f+this.random.nextDouble()*2-1;
		this.gravity=-0.05f;
		this.hasPhysics = true;
		this.xd = vx * 1;
		this.yd = vy * 1;
		this.zd = vz * 1;
		float rc=(float)Math.min(1,1-(this.random.nextDouble()/4));
		this.rCol=rc;
		this.gCol=rc;
		this.bCol=rc;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		float p=(float)this.age/(float)this.lifetime;
		if(0.3<p)this.setSprite(this.spriteSet.get((int)(16.0/(this.lifetime*0.7)*(this.age-this.lifetime*0.3))+1,16));
	}
}