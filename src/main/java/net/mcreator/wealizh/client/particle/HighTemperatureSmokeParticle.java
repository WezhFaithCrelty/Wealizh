package net.mcreator.wealizh.client.particle;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

public class HighTemperatureSmokeParticle extends TextureSheetParticle {
	public static HighTemperatureSmokeParticleProvider provider(SpriteSet spriteSet) {
		return new HighTemperatureSmokeParticleProvider(spriteSet);
	}

	public static class HighTemperatureSmokeParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public HighTemperatureSmokeParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new HighTemperatureSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected HighTemperatureSmokeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(1f, 1f);
		this.quadSize *= 42f;
		this.lifetime = 2300;
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
	    if (p < 0.15f) {
	        float t = p / 0.15f;
	        this.rCol = 1.0f;
	        this.gCol = 1.0f - t * 0.157f;
	        this.bCol = 1.0f - t;
	    } 
	    else if (p < 0.30f) {
	        float t = (p - 0.15f) / 0.15f;
	        this.rCol = 1.0f - t * 0.78f;
	        this.gCol = 0.843f - t * 0.655f;
	        this.bCol = 0.0f + t * 0.165f;
	    } 
	    else {
	        this.rCol = 0.22f;
	        this.gCol = 0.188f;
	        this.bCol = 0.165f;
	    }if(0.9<p)this.setSprite(this.spriteSet.get((int)(16.0/(this.lifetime*0.1)*(this.age-this.lifetime*0.9))+1,16));
	}
}