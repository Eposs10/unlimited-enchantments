package dev.eposs.unlimitedenchantments.config;

import dev.eposs.unlimitedenchantments.UnlimitedEnchantments;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Config(name = UnlimitedEnchantments.MOD_ID)
public class ModConfig implements ConfigData {
    public static ModConfig getConfig() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static void save() {
        AutoConfig.getConfigHolder(ModConfig.class).save();
    }
    
    public int maxLevel = 0;
    public int maxExp = 38;

    @Override
    public void validatePostLoad() throws ValidationException {
        if (maxLevel < 0) maxLevel = 0;
        if (maxExp < 0) maxExp = 0;
    }
}
