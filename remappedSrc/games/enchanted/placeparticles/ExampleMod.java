package games.enchanted.placeparticles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import games.enchanted.placeparticles.particle.Particles;
import games.enchanted.placeparticles.particle.custom.FallingBlockParticle;
import games.enchanted.placeparticles.particle.custom.PlacedBlockParticle;

public class ExampleMod implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("place-particles");

	public static final String MOD_ID = "place-particles";

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Particles.registerParticles();
		ParticleFactoryRegistry.getInstance().register(Particles.FALLING_BLOCK_PARTICLE, FallingBlockParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(Particles.PLACED_BLOCK_PARTICLE, PlacedBlockParticle.Factory::new);

		LOGGER.info("Hello Fabric world!");
	}
}