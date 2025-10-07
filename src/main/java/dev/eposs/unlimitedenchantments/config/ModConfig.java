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

    public int getMaxLevel() {
        if (maxLevel <= 0) return Integer.MAX_VALUE;
        return maxLevel;
    }

    public int getMaxExp() {
        if (maxExp <= 0) return Integer.MAX_VALUE;
        return maxExp;
    }
}
