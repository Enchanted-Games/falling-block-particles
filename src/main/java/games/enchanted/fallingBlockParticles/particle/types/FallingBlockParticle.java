package games.enchanted.fallingBlockParticles.particle.types;

import games.enchanted.fallingBlockParticles.FallingBlockParticlesClient;
import games.enchanted.fallingBlockParticles.config.ConfigValues;
import games.enchanted.fallingBlockParticles.util.NumberUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.client.particle.BlockDustParticle;

@Environment(value = EnvType.CLIENT)
public class FallingBlockParticle extends BlockDustParticle {
  private final int minAgeInTicks = ConfigValues.falling_block_particle__minAgeInTicks;
  private final int maxAgeInTicks = ConfigValues.falling_block_particle__maxAgeInTicks;
  private final int scaleTimeInTicks = ConfigValues.falling_block_particle__scaleTimeInTicks;
  private final int scaleDelayInTicks = ConfigValues.falling_block_particle__scaleDelayInTicks;

  protected float initialScale = 0.0f;
  protected boolean hasLanded = false;
  protected int ageWhenLanded = 0;

  FallingBlockParticle(ClientWorld world, double x, double y, double z, BlockState state) {
    super(world, x, y, z, z, z, z, state);
    this.setSprite(MinecraftClient.getInstance().getBlockRenderManager().getModels().getModelParticleSprite(state));

    this.velocityY = 0.0f;
    this.gravityStrength = 0.4f;
    this.initialScale = scale;
    
    this.maxAge = minAgeInTicks + (int)(Math.random() * ((maxAgeInTicks - minAgeInTicks) + 1));
  }

  @Override
  public void tick() {
    if ( this.age++ >= this.maxAge || this.scale <= 0 ) {
      this.markDead();
      return;
    }

    if( this.hasLanded ) {
      float lerpAmount = (float) ((this.age - this.ageWhenLanded) - scaleDelayInTicks) / (float) (scaleTimeInTicks);
      float newScale = NumberUtil.lerp(this.initialScale, 0.0f, lerpAmount);
      if(newScale > this.initialScale || newScale < 0) {
        return;
      };
      this.scale = newScale;
    }

    if ( this.collidesWithWorld && this.prevPosY == this.y && this.age >= 10 && !this.hasLanded ) {
      this.hasLanded = true;
      this.ageWhenLanded = this.age;
      this.maxAge = this.age + scaleDelayInTicks + scaleTimeInTicks;
      this.y += 0.0001;
      return;
    }

    this.prevPosX = this.x;
    this.prevPosY = this.y;
    this.prevPosZ = this.z;
    
    this.move(0, this.velocityY, 0);
    
    this.velocityY -= 0.04f * (double) this.gravityStrength;
  }

  @Environment(EnvType.CLIENT)
  public static class Factory implements ParticleFactory<BlockStateParticleEffect> {
    @SuppressWarnings("unused")
    private final SpriteProvider sprites;

    public Factory(SpriteProvider spriteSet) {
      this.sprites = spriteSet;
    }

    @Override
    public Particle createParticle(BlockStateParticleEffect blockStateParticleEffect, ClientWorld clientWorld, double d,
        double e, double f, double g, double h, double i) {
      return new FallingBlockParticle(clientWorld, d, e, f, blockStateParticleEffect.getBlockState());
    }

  }
}