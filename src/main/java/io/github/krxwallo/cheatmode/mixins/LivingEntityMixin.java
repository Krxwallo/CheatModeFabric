package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.ConfigManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "getAttributeValue", locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    public void getAttributeValue(RegistryEntry<EntityAttribute> attributeEntry, CallbackInfoReturnable<Double> cir) {
        if (attributeEntry == EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE) {
            cir.setReturnValue(ConfigManager.INSTANCE.getClient().getInteractionReach());
        }
        else if (attributeEntry == EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE) {
            cir.setReturnValue(ConfigManager.INSTANCE.getClient().getBlockReach());
        }
    }
}
