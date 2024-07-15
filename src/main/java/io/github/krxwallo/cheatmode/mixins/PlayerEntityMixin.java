package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.ConfigManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @ModifyConstant(method = "attack", constant = @Constant(doubleValue = 9.0))
    private double modifyInteractionRange(double original) {
        return MathHelper.square(ConfigManager.INSTANCE.getClient().getInteractionReach());
    }
}
