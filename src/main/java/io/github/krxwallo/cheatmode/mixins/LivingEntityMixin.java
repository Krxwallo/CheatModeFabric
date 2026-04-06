package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.ConfigManager;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "getAttributeValue", locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    public void getAttributeValue(Holder<Attribute> attributeEntry, CallbackInfoReturnable<Double> cir) {
        if (attributeEntry == Attributes.ENTITY_INTERACTION_RANGE) {
            cir.setReturnValue(ConfigManager.INSTANCE.getClient().getInteractionReach());
        }
        else if (attributeEntry == Attributes.BLOCK_INTERACTION_RANGE) {
            cir.setReturnValue(ConfigManager.INSTANCE.getClient().getBlockReach());
        }
    }
}
