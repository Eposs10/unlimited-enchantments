package dev.eposs.unlimitedenchantments.mixin;

import dev.eposs.unlimitedenchantments.config.ModConfig;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin({AnvilScreenHandler.class})
public abstract class AnvilScreenHandlerMixin {
    public AnvilScreenHandlerMixin() {
    }

    @Final
    @Shadow
    private Property levelCost;
    
    @Inject(method = {"updateResult"}, at = {@At("RETURN")})
    private void updateResultInject(CallbackInfo ci) {
        if (levelCost.get() > ModConfig.getConfig().maxExp) levelCost.set(ModConfig.getConfig().maxExp);
    }

    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        return Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 39))
    private int mixinMaxInt(int i) {
        return Integer.MAX_VALUE - 1;
    }
}
