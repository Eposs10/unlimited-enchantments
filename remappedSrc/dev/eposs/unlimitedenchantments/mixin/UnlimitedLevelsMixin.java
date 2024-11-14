package dev.eposs.unlimitedenchantments.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(
        priority = 500,
        value = {AnvilScreenHandler.class}
)
public abstract class UnlimitedLevelsMixin {
    public UnlimitedLevelsMixin() {
    }

    @Redirect(
            method = {"updateResult"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"
            )
    )
    private int unlimitedLevels(Enchantment enchantment) {
        return Integer.MAX_VALUE;
    }
}
