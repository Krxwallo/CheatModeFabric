package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.ConfigManager;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Player.class)
public class PlayerEntityMixin {
    @ModifyConstant(method = "attack", constant = @Constant(doubleValue = 9.0))
    private double modifyInteractionRange(double original) {
        return Mth.square(ConfigManager.INSTANCE.getClient().getInteractionReach());
    }
}
