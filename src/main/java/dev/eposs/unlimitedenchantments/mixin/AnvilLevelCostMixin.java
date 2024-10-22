package dev.eposs.unlimitedenchantments.mixin;

import dev.eposs.unlimitedenchantments.UnlimitedEnchantments;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AnvilScreenHandler.class})
public abstract class AnvilLevelCostMixin {
    public AnvilLevelCostMixin() {
    }

    @Final
    @Shadow
    private Property levelCost;

    @Inject(
            method = {"getLevelCost"},
            at = {@At("RETURN")},
            cancellable = true
    )
    private void getContext(@NotNull CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValueI() >= UnlimitedEnchantments.MAX_LEVEL) {
            cir.setReturnValue(UnlimitedEnchantments.MAX_LEVEL);
        }
    }

    @Inject(
            method = {"getNextCost"},
            at = {@At("RETURN")},
            cancellable = true
    )
    private static void getContext(int cost, CallbackInfoReturnable<Integer> cir) {
        if (cost >= UnlimitedEnchantments.MAX_LEVEL) {
            cir.setReturnValue(UnlimitedEnchantments.MAX_LEVEL);
        }
    }

    @Inject(
            method = {"updateResult"},
            at = {@At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;get()I")}
    )
    private void updateResultInject(CallbackInfo ci) {
        if (levelCost.get() >= UnlimitedEnchantments.MAX_LEVEL) levelCost.set(UnlimitedEnchantments.MAX_LEVEL);
    }
}
