package games.enchanted.fallingBlockParticles.integrations;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import games.enchanted.fallingBlockParticles.config.YACLConfigBuilder;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            return YACLConfigBuilder.buildScreen(parent);
        };
    }
}