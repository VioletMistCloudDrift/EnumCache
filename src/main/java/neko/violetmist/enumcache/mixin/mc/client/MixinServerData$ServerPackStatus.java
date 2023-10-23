package neko.violetmist.enumcache.mixin.mc.client;

import neko.violetmist.enumcache.api.ICacheableEnum;
import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerData.ServerPackStatus.class)
public abstract class MixinServerData$ServerPackStatus implements ICacheableEnum {
    @Unique
    private static ServerData.ServerPackStatus[] enumCache$cache = null;

    @Override
    public void enumCache$clearValuesCache() {
        enumCache$cache = null;
    }

    @Inject(method = "values", at = @At("HEAD"), cancellable = true)
    private static void enumCache$inject$values$head(CallbackInfoReturnable<ServerData.ServerPackStatus[]> cir) {
        if (enumCache$cache != null) cir.setReturnValue(enumCache$cache);
    }

    @Inject(method = "values", at = @At("RETURN"))
    private static void enumCache$inject$values$tail(CallbackInfoReturnable<ServerData.ServerPackStatus[]> cir) {
        if (enumCache$cache == null) enumCache$cache = cir.getReturnValue();
    }
}
