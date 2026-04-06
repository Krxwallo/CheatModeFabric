package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.ConfigManager;
import io.github.krxwallo.cheatmode.ManagerKt;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Abilities;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LocalPlayer.class)
public class ClientPlayerMixin {
    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;mayfly:Z", opcode = Opcodes.GETFIELD), method = "aiStep")
    public boolean mayfly(Abilities instance) {
        if (ManagerKt.getMc().gameMode == null) return false;
        return ManagerKt.getMc().gameMode.getPlayerMode().isCreative() || ConfigManager.INSTANCE.getClient().getFlight();
    }
}
