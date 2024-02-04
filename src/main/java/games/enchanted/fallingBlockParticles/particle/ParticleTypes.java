package games.enchanted.fallingBlockParticles.particle;

import com.mojang.serialization.Codec;

import games.enchanted.fallingBlockParticles.FallingBlockParticlesClient;
import games.enchanted.fallingBlockParticles.particle.types.FallingBlockParticle;

import java.util.function.Function;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleTypes {

    public static final ParticleType<BlockStateParticleEffect> FALLING_BLOCK_PARTICLE = ParticleTypes.register("falling_block", false, BlockStateParticleEffect.PARAMETERS_FACTORY,BlockStateParticleEffect::createCodec);

    public static void registerParticles() {
       ParticleFactoryRegistry.getInstance().register(ParticleTypes.FALLING_BLOCK_PARTICLE, FallingBlockParticle.Factory::new);
    }

    private static <T extends ParticleEffect> ParticleType<T> register(String name, boolean alwaysShow, ParticleEffect.Factory<T> factory, final Function<ParticleType<T>, Codec<T>> codecGetter) {
      return Registry.register(Registries.PARTICLE_TYPE, new Identifier(FallingBlockParticlesClient.MOD_ID, name), new ParticleType<T>(alwaysShow, factory){

        @Override
        public Codec<T> getCodec() {
          return (Codec)codecGetter.apply(this);
        }
      });
    }
}
