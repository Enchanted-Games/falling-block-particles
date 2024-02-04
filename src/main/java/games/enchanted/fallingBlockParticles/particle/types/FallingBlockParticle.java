package games.enchanted.fallingBlockParticles.particle.types;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.particle.BlockDustParticle;


@Environment(value = EnvType.CLIENT)
public class FallingBlockParticle extends BlockDustParticle {

  private BlockState blockState;

  FallingBlockParticle(ClientWorld world, double x, double y, double z, BlockState state) {
    super(world, x, y, z, z, z, z, state);
    this.setSprite(MinecraftClient.getInstance().getBlockRenderManager().getModels().getModelParticleSprite(state));

    this.velocityY = 0.0f;
    this.blockState = state;
    
    this.gravityStrength = 0.4f;
    int i = (int)(32.0 / (Math.random() * 0.8 + 0.2));
    this.maxAge = (int)Math.max((float)i * 0.9f, 1.0f);
  }

  @Override
  public void tick() {
    this.prevPosX = this.x;
    this.prevPosY = this.y;
    this.prevPosZ = this.z;
    if (this.age++ >= this.maxAge) {
      this.markDead();
      return;
    }

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