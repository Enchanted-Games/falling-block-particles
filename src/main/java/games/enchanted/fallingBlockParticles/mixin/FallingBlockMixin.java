package games.enchanted.fallingBlockParticles.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.LandingBlock;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;

import games.enchanted.fallingBlockParticles.config.ConfigValues;
import games.enchanted.fallingBlockParticles.particle.ParticleTypes;

@Mixin(FallingBlock.class)
public class FallingBlockMixin extends Block implements LandingBlock {

	public FallingBlockMixin(Settings settings) {
		super(settings);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(ConfigValues.falling_block_particle__rarity) == 0 && !Block.hasTopRim(world, pos.down())) {
			double d = (double) pos.getX() + random.nextDouble();
			double e = (double) pos.getY() - 0.05f;
			double f = (double) pos.getZ() + random.nextDouble();
			world.addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_BLOCK_PARTICLE, state), d, e, f, 0.0, 0.0, 0.0);
		}
	}
}