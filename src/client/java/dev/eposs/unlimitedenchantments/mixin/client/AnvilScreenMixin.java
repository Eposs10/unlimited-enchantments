package dev.eposs.unlimitedenchantments.mixin.client;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin {
    public AnvilScreenMixin() {
    }

    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
    private int mixinTooExpensive(int constant) {
        return Integer.MAX_VALUE;
    }
}
