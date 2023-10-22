package neko.violetmist.enumcache.mixin.mc.common;

import neko.violetmist.enumcache.api.ICacheableEnum;
import net.minecraft.util.BlockVoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockVoxelShape.class)
public abstract class MixinBlockVoxelShape implements ICacheableEnum {
    @Unique
    private static BlockVoxelShape[] enumCache$cache = null;

    @Override
    public void enumCache$clearValuesCache() {
        enumCache$cache = null;
    }

    @Inject(method = "values", at = @At("HEAD"), cancellable = true)
    private static void enumCache$inject$values$head(CallbackInfoReturnable<BlockVoxelShape[]> cir) {
        if (enumCache$cache != null) cir.setReturnValue(enumCache$cache);
    }

    @Inject(method = "values", at = @At("RETURN"))
    private static void enumCache$inject$values$tail(CallbackInfoReturnable<BlockVoxelShape[]> cir) {
        if (enumCache$cache == null) enumCache$cache = cir.getReturnValue();
    }
}
