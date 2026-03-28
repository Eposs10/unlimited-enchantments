package dev.eposs.unlimitedenchantments.mixin;

import dev.eposs.unlimitedenchantments.config.ModConfig;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({AnvilMenu.class})
public abstract class AnvilMenuMixin {
    public AnvilMenuMixin() {
    }

    @Final
    @Shadow
    private DataSlot cost;

    @Redirect(
            method = {"createResult"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"
            )
    )
    private int unlimitedLevels(Enchantment enchantment) {
        return ModConfig.getConfig().getMaxLevel();
    }

    @Inject(method = {"createResult"}, at = {@At("RETURN")})
    private void createResultInject(CallbackInfo ci) {
        if (cost.get() > ModConfig.getConfig().getMaxExp()) cost.set(ModConfig.getConfig().getMaxExp());
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        return Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 39))
    private int mixinMaxInt(int i) {
        return Integer.MAX_VALUE - 1;
    }
}
