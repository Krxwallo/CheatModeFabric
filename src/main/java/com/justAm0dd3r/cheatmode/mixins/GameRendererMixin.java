package com.justAm0dd3r.cheatmode.mixins;

// TODO fix reach/range problems
/*@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @ModifyConstant(method = "updateTargetedEntity", constant = @Constant(doubleValue = 3.0))
    public double getAttackRange(double d) {
        return InteractionManagerHooks.INSTANCE.getReach();
    }

    @ModifyConstant(method = "updateTargetedEntity", constant = @Constant(doubleValue = 6.0))
    public double getPickDistance(double d) {
        return Math.max(InteractionManagerHooks.INSTANCE.getReach(), getAttackRange(3.0)); // arg doesn't matter
    }

    @ModifyVariable(method = "updateTargetedEntity", at = @At("LOAD"))
    public double get
}
*/