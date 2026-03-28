package dev.eposs.unlimitedenchantments.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.*;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Enchantment.class})
public abstract class RomanLevelTranslationMixin {
    @Unique
    private static @NotNull String intToRoman(int num) {
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLetters = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; ++i) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLetters[i]);
            }
        }

        return roman.toString();
    }

    @Inject(
            method = {"getFullname"},
            at = {@At("RETURN")},
            cancellable = true
    )
    private static void levelToRoman(Holder<Enchantment> enchantment, int level, CallbackInfoReturnable<Component> cir) {
        MutableComponent mutableComponent = enchantment.value().description().copy();
        if (enchantment.is(EnchantmentTags.CURSE)) {
            mutableComponent = ComponentUtils.mergeStyles(mutableComponent, Style.EMPTY.withColor(ChatFormatting.RED));
        } else {
            mutableComponent = ComponentUtils.mergeStyles(mutableComponent, Style.EMPTY.withColor(ChatFormatting.GRAY));
        }
        if (level != 1) {
            mutableComponent.append(CommonComponents.SPACE).append(intToRoman(level));
        }

        cir.setReturnValue(mutableComponent);
    }
}