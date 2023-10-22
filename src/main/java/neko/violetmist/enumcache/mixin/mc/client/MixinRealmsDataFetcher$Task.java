package neko.violetmist.enumcache.mixin.mc.client;

import com.mojang.realmsclient.gui.RealmsDataFetcher;
import neko.violetmist.enumcache.api.ICacheableEnum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RealmsDataFetcher.Task.class)
public abstract class MixinRealmsDataFetcher$Task implements ICacheableEnum {
    @Unique
    private static RealmsDataFetcher.Task[] enumCache$cache = null;

    @Override
    public void enumCache$clearValuesCache() {
        enumCache$cache = null;
    }

    @Inject(method = "values", at = @At("HEAD"), cancellable = true)
    private static void enumCache$inject$values$head(CallbackInfoReturnable<RealmsDataFetcher.Task[]> cir) {
        if (enumCache$cache != null) cir.setReturnValue(enumCache$cache);
    }

    @Inject(method = "values", at = @At("RETURN"))
    private static void enumCache$inject$values$tail(CallbackInfoReturnable<RealmsDataFetcher.Task[]> cir) {
        if (enumCache$cache == null) enumCache$cache = cir.getReturnValue();
    }
}
