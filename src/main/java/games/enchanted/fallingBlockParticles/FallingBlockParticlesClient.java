package games.enchanted.fallingBlockParticles;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import games.enchanted.fallingBlockParticles.particle.ParticleTypes;

public class FallingBlockParticlesClient implements ClientModInitializer {
	public static final String MOD_ID = "falling-block-particles";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		ParticleTypes.registerParticles();
	}
}