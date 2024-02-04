package games.enchanted.placeparticles.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.LandingBlock;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;

import games.enchanted.placeparticles.particle.Particles;

@Mixin(FallingBlock.class)
public class ExampleMixin
		extends Block
		implements LandingBlock {

	public ExampleMixin(Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
	}

	// @Inject(at = @At("HEAD"), method =
	// "Lnet/minecraft/block/FallingBlock;randomDisplayTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V")
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(12) == 0 && FallingBlock.canFallThrough(world.getBlockState(pos.down()))) {
			double d = (double) pos.getX() + random.nextDouble();
			double e = (double) pos.getY() - 0.05f;
			double f = (double) pos.getZ() + random.nextDouble();
			world.addParticle(new BlockStateParticleEffect(Particles.FALLING_BLOCK_PARTICLE, state), d, e, f, 0.0, 0.0, 0.0);
		}
	}
}