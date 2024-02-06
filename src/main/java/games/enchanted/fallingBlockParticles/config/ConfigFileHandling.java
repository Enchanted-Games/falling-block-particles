package games.enchanted.fallingBlockParticles.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import games.enchanted.fallingBlockParticles.FallingBlockParticlesClient;
import games.enchanted.fallingBlockParticles.util.Conversions;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigFileHandling {
    public static final String FBP_RARITY = "falling_block_particle__rarity";
    public static final String FBP_MIN_TICK_AGE = "falling_block_particle__min_age_in_ticks";
    public static final String FBP_MAX_TICK_AGE = "falling_block_particle__max_age_in_ticks";
    public static final String FBP_SCALE_TIME = "falling_block_particle__scale_time_in_ticks";
    public static final String FBP_SCALE_DELAY = "falling_block_particle__scale_delay_in_ticks";
    
    private static void writeProperties(Properties p) {
        p.setProperty(FBP_RARITY, Integer.toString(ConfigValues.falling_block_particle__rarity));
        p.setProperty(FBP_MIN_TICK_AGE, Integer.toString(ConfigValues.falling_block_particle__minAgeInTicks));
        p.setProperty(FBP_MAX_TICK_AGE, Integer.toString(ConfigValues.falling_block_particle__maxAgeInTicks));
        p.setProperty(FBP_SCALE_TIME, Integer.toString(ConfigValues.falling_block_particle__scaleTimeInTicks));
        p.setProperty(FBP_SCALE_DELAY, Integer.toString(ConfigValues.falling_block_particle__scaleDelayInTicks));
    }

    private static void setConfigFromProperties(Properties p) {
        ConfigValues.falling_block_particle__rarity = Conversions.defaultedStringToInt(getProperty(p, FBP_RARITY), DefaultConfigValues.falling_block_particle__rarity);
        ConfigValues.falling_block_particle__minAgeInTicks = Conversions.defaultedStringToInt(getProperty(p, FBP_MIN_TICK_AGE), ConfigValues.falling_block_particle__minAgeInTicks);
        ConfigValues.falling_block_particle__maxAgeInTicks = Conversions.defaultedStringToInt(getProperty(p, FBP_MAX_TICK_AGE), ConfigValues.falling_block_particle__maxAgeInTicks);
        ConfigValues.falling_block_particle__scaleTimeInTicks = Conversions.defaultedStringToInt(getProperty(p, FBP_SCALE_TIME), ConfigValues.falling_block_particle__scaleTimeInTicks);
        ConfigValues.falling_block_particle__scaleDelayInTicks = Conversions.defaultedStringToInt(getProperty(p, FBP_SCALE_DELAY), ConfigValues.falling_block_particle__scaleDelayInTicks);
    }


    // Call loadConfig before saving in onInitialize!
    public static void saveConfig() {
        Properties configProperties = new Properties();
        Path configFilePath = FabricLoader.getInstance().getConfigDir().resolve(FallingBlockParticlesClient.CONFIG_FILE_NAME);

        writeProperties(configProperties);

        // create file if it doesn't already exist
        if(!Files.exists(configFilePath) ) {
            try {
                Files.createFile(configFilePath);
            } catch ( IOException exception ) {
                FallingBlockParticlesClient.log.error("Could not create config file \"" + FallingBlockParticlesClient.CONFIG_FILE_NAME + "\"");
                exception.printStackTrace();
                return;
            }
        }

        // save properties to file
        try {
            configProperties.store(Files.newOutputStream(configFilePath), " -- Config file for " + FallingBlockParticlesClient.MOD_ID + " -- ");
        } catch ( IOException exception ) {
            FallingBlockParticlesClient.log.error("Could not write to config file \"" + FallingBlockParticlesClient.CONFIG_FILE_NAME + "\"");
            exception.printStackTrace();
        }
    }
    
    public static void loadConfig() {
        Properties configProperties = new Properties();
        Path configFilePath = FabricLoader.getInstance().getConfigDir().resolve(FallingBlockParticlesClient.CONFIG_FILE_NAME);

        // save defaults if no config exists
        if( !Files.exists(configFilePath) ) {
            saveConfig();
        }

        // read properties from config file
        try {
            configProperties.load( Files.newInputStream(configFilePath) );
        } catch ( IOException exception ) {
            FallingBlockParticlesClient.log.error("Could not read config file \"" + FallingBlockParticlesClient.CONFIG_FILE_NAME + "\"");
            exception.printStackTrace();
            return;
        }

        setConfigFromProperties(configProperties);
    }
    
    private static String getProperty(Properties p, String property) {
        String prop = p.getProperty(property);
        return prop == null ? "" : prop;
    }
}
