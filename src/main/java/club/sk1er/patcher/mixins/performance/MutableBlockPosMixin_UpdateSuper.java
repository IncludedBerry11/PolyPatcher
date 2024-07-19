package club.sk1er.patcher.mixins.performance;

import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockPos.MutableBlockPos.class)
public class MutableBlockPosMixin_UpdateSuper extends Vec3i {
    public MutableBlockPosMixin_UpdateSuper(int i, int j, int k) {
        super(i, j, k);
    }

    @Inject(method = "<init>(III)V", at = @At("RETURN"))
    private void patcher$initSuper(int x_, int y_, int z_, CallbackInfo ci) {
        super.x = x_;
        super.y = y_;
        super.z = z_;
    }

    /**
     * @author xtrm
     * @reason Update underlying superclass too
     */
    @Overwrite
    public BlockPos.MutableBlockPos set(int xIn, int yIn, int zIn) {
        super.x = xIn;
        super.y = yIn;
        super.z = zIn;
        return (BlockPos.MutableBlockPos) (Object) this;
    }

    /**
     * @author xtrm
     * @reason idk
     */
    @Overwrite
    public int getX() {
        return super.x;
    }

    /**
     * @author xtrm
     * @reason idk
     */
    @Overwrite
    public int getY() {
        return super.y;
    }

    /**
     * @author xtrm
     * @reason idk
     */
    @Overwrite
    public int getZ() {
        return super.z;
    }

    @Inject(method = "access$002", at = @At("HEAD"))
    private static void patcher$storeSuperX(BlockPos.MutableBlockPos x0, int x1, CallbackInfoReturnable<Integer> cir) {
        ((Vec3i) x0).x = x1;
    }

    @Inject(method = "access$102", at = @At("HEAD"))
    private static void patcher$storeSuperY(BlockPos.MutableBlockPos y0, int y1, CallbackInfoReturnable<Integer> cir) {
        ((Vec3i) y0).y = y1;
    }

    @Inject(method = "access$202", at = @At("HEAD"))
    private static void patcher$storeSuperZ(BlockPos.MutableBlockPos z0, int z1, CallbackInfoReturnable<Integer> cir) {
        ((Vec3i) z0).z = z1;
    }
}
