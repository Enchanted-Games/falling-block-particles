package games.enchanted.fallingBlockParticles.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import games.enchanted.fallingBlockParticles.FallingBlockParticlesClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class YACLConfigBuilder {
    private final static String translationStart = FallingBlockParticlesClient.MOD_ID;
    // https://github.com/isXander/Controlify/blob/update/1.20.3/src/main/java/dev/isxander/controlify/gui/screen/GlobalSettingsScreenFactory.java
    public static Screen buildScreen(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
            .title( Text.translatableWithFallback(translationStart + ".config.title", "Configurable Item Timers Config") )
            // main category
            .category( ConfigCategory.createBuilder()
                .name( Text.translatableWithFallback(translationStart + ".config.category.particles", "particles") )

                // general group
                .group( OptionGroup.createBuilder()
                    .name( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle", "falling block dust particle") )
                    // particle rarity
                    .option( Option.<Integer>createBuilder()
                        .name( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.rarity", "particle spawn chance") )
                        .description( OptionDescription.of( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.rarity.desc", "higher values reduce the chance of particles spawning") ) )
                        .binding( DefaultConfigValues.falling_block_particle__rarity, () -> ConfigValues.falling_block_particle__rarity, (newVal) -> ConfigValues.falling_block_particle__rarity = newVal )
                        .controller( opt -> IntegerSliderControllerBuilder.create(opt).range(1, 64).step(1) )
                    .build())
                    // min age
                    .option( Option.<Integer>createBuilder()
                        .name( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.minAgeInTicks", "min particle lifespan (ticks)") )
                        .description( OptionDescription.of( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.minAgeInTicks.desc", "minimum amount of time particles can live for, in ticks") ) )
                        .binding( DefaultConfigValues.falling_block_particle__minAgeInTicks, () -> ConfigValues.falling_block_particle__minAgeInTicks, (newVal) -> ConfigValues.falling_block_particle__minAgeInTicks = newVal )
                        .controller( opt -> IntegerSliderControllerBuilder.create(opt).range(0, 512).step(1) )
                    .build())
                    // max age
                    .option( Option.<Integer>createBuilder()
                        .name( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.maxAgeInTicks", "max particle lifespan (ticks)") )
                        .description( OptionDescription.of( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.maxAgeInTicks.desc", "maximum amount of time particles can live for, in ticks") ) )
                        .binding( DefaultConfigValues.falling_block_particle__maxAgeInTicks, () -> ConfigValues.falling_block_particle__maxAgeInTicks, (newVal) -> ConfigValues.falling_block_particle__maxAgeInTicks = newVal )
                        .controller( opt -> IntegerSliderControllerBuilder.create(opt).range(0, 512).step(1) )
                    .build())
                    // scale time
                    .option( Option.<Integer>createBuilder()
                        .name( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.scaleTimeInTicks", "scale time (ticks)") )
                        .description( OptionDescription.of( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.scaleTimeInTicks.desc", "time it takes for particle to shrink and dissapear, in ticks") ) )
                        .binding( DefaultConfigValues.falling_block_particle__scaleTimeInTicks, () -> ConfigValues.falling_block_particle__scaleTimeInTicks, (newVal) -> ConfigValues.falling_block_particle__scaleTimeInTicks = newVal )
                        .controller( opt -> IntegerSliderControllerBuilder.create(opt).range(0, 512).step(1) )
                    .build())
                    // scale delay
                    .option( Option.<Integer>createBuilder()
                        .name( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.scaleDelayInTicks", "scale Delay (ticks)") )
                        .description( OptionDescription.of( Text.translatableWithFallback(translationStart + ".config.group.falling_block_particle.option.scaleDelayInTicks.desc", "deblay before the particle starts to shrink and disapear, in ticks") ) )
                        .binding( DefaultConfigValues.falling_block_particle__scaleDelayInTicks, () -> ConfigValues.falling_block_particle__scaleDelayInTicks, (newVal) -> ConfigValues.falling_block_particle__scaleDelayInTicks = newVal )
                        .controller( opt -> IntegerSliderControllerBuilder.create(opt).range(0, 512).step(1) )
                    .build())

                .build())

            .build())

            .save(new Runnable() {
                @Override
                public void run() {
                    ConfigFileHandling.saveConfig();
                }
            })
        .build().generateScreen(parent);
    }
}
