package org.vivecraft.mixin_mod_compat_vr.sodium;

import me.jellysquid.mods.sodium.client.render.chunk.RenderSectionManager;
import me.jellysquid.mods.sodium.client.render.chunk.lists.ChunkRenderList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderSectionManager.class)
public class RenderSectionManagerVRMixin {

    @Shadow
    private ChunkRenderList chunkRenderList;

    @Inject(at = @At("HEAD"), method = "getVisibleChunkCount", cancellable = true, remap = false)
    private void preventNullpointerException(CallbackInfoReturnable<Integer> cir){
        if (chunkRenderList == null) {
            cir.setReturnValue(-1);
        }
    }
}