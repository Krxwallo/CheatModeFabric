package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.ConfigManager;
import io.github.krxwallo.cheatmode.ManagerKt;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin {
    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;allowFlying:Z", opcode = Opcodes.GETFIELD), method = "tickMovement")
    public boolean mayfly(PlayerAbilities instance) {
        if (ManagerKt.getMc().interactionManager == null) return false;
        return ManagerKt.getMc().interactionManager.getCurrentGameMode().isCreative() || ConfigManager.INSTANCE.getClient().getFlight();
    }
}
