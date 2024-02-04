package games.enchanted.placeparticles.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.util.math.BlockPos;

@Environment(value = EnvType.CLIENT)
public class FallingBlockParticle
    extends SpriteBillboardParticle {

  private final BlockPos blockPos;
  private final float sampleU;
  private final float sampleV;

  FallingBlockParticle(ClientWorld world, double x, double y, double z, BlockState state) {
    super(world, x, y, z);
    this.blockPos = BlockPos.ofFloored(x, y, z);
    this.setSprite(MinecraftClient.getInstance().getBlockRenderManager().getModels().getModelParticleSprite(state));
    this.gravityStrength = 0.5f;
    // this.maxAge = 40;
    this.maxAge = (int) ((Math.random() * (150 - 50)) + 30);
    this.collidesWithWorld = true;

    this.sampleU = this.random.nextFloat() * 3.0f;
    this.sampleV = this.random.nextFloat() * 3.0f;

    this.scale /= 2.0f;
  }

  @Override
  public ParticleTextureSheet getType() {
    return ParticleTextureSheet.TERRAIN_SHEET;
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

    this.move(this.velocityX, this.velocityY, this.velocityZ);
    // this.velocityY -= (double) 0.003f;
    // this.velocityY = Math.max(this.velocityY, (double) -0.14f);

    this.velocityY -= 0.04 * (double) this.gravityStrength;
  }

  @Override
  protected float getMinU() {
    return this.sprite.getFrameU((this.sampleU + 1.0f) / 4.0f * 16.0f);
  }

  @Override
  protected float getMaxU() {
    return this.sprite.getFrameU(this.sampleU / 4.0f * 16.0f);
  }

  @Override
  protected float getMinV() {
    return this.sprite.getFrameV(this.sampleV / 4.0f * 16.0f);
  }

  @Override
  protected float getMaxV() {
    return this.sprite.getFrameV((this.sampleV + 1.0f) / 4.0f * 16.0f);
  }

  @Override
  public int getBrightness(float tint) {
    int i = super.getBrightness(tint);
    if (i == 0 && this.world.isChunkLoaded(this.blockPos)) {
      return WorldRenderer.getLightmapCoordinates(this.world, this.blockPos);
    }
    return i;
  }

  @Environment(EnvType.CLIENT)
  public static class Factory implements ParticleFactory<BlockStateParticleEffect> {
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