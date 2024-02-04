/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package games.enchanted.placeparticles.particle;

import com.mojang.serialization.Codec;

import games.enchanted.placeparticles.ExampleMod;

import java.util.function.Function;

import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Particles {

  public static final ParticleType<BlockStateParticleEffect> FALLING_BLOCK_PARTICLE = Particles.register(
      "falling_block", false, BlockStateParticleEffect.PARAMETERS_FACTORY,
      BlockStateParticleEffect::createCodec);
  public static final ParticleType<BlockStateParticleEffect> PLACED_BLOCK_PARTICLE = Particles.register(
      "placed_block", false, BlockStateParticleEffect.PARAMETERS_FACTORY,
      BlockStateParticleEffect::createCodec);

  public static void registerParticles() {
    // Registry.register(Registries.PARTICLE_TYPE, new Identifier(ExampleMod.MOD_ID,
    // "falling_block_particle"),
    // FALLING_BLOCK_PARTICLE);
  }

  private static <T extends ParticleEffect> ParticleType<T> register(String name, boolean alwaysShow,
      ParticleEffect.Factory<T> factory, final Function<ParticleType<T>, Codec<T>> codecGetter) {
    return Registry.register(Registries.PARTICLE_TYPE, new Identifier(ExampleMod.MOD_ID, name),
        new ParticleType<T>(alwaysShow, factory) {

          @Override
          public Codec<T> getCodec() {
            return (Codec) codecGetter.apply(this);
          }
        });
  }
}
