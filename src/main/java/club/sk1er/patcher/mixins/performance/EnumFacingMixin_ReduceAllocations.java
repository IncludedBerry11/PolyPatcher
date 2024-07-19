/**
 * THE FOLLOWING CODE IS LICENSED UNDER THE GNU Lesser General Public License v3.0
 * This work, "PolyPatcher", uses code from CaffeineMC's "lithium-fabric", licensed under the LGPL-3.0 license. The original license is included in the repository.
 * https://github.com/CaffeineMC/lithium-fabric/tree/develop
 * https://github.com/CaffeineMC/lithium-fabric/blob/develop/LICENSE.txt
 */
package club.sk1er.patcher.mixins.performance;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EnumFacing.class)
public class EnumFacingMixin_ReduceAllocations {

    @Shadow
    @Final
    public static EnumFacing[] VALUES;
    @Shadow
    @Final
    private int opposite;

    @Unique
    private int patcher$frontOffsetX;
    @Unique
    private int patcher$frontOffsetY;
    @Unique
    private int patcher$frontOffsetZ;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void cacheOffsets(String string, int i, int indexIn, int oppositeIn, int horizontalIndexIn, String nameIn, EnumFacing.AxisDirection axisDirectionIn, EnumFacing.Axis axisIn, Vec3i directionVecIn, CallbackInfo ci) {
        patcher$frontOffsetX = axisIn == EnumFacing.Axis.X ? axisDirectionIn.getOffset() : 0;
        patcher$frontOffsetY = axisIn == EnumFacing.Axis.Y ? axisDirectionIn.getOffset() : 0;
        patcher$frontOffsetZ = axisIn == EnumFacing.Axis.Z ? axisDirectionIn.getOffset() : 0;
    }

    /**
     * @author jellysquid
     * @reason The cached front offset X
     */
    @Overwrite
    public int getFrontOffsetX() {
        return patcher$frontOffsetX;
    }

    /**
     * @author jellysquid
     * @reason The cached front offset Y
     */
    @Overwrite
    public int getFrontOffsetY() {
        return patcher$frontOffsetY;
    }

    /**
     * @author jellysquid
     * @reason The cached front offset Z
     */
    @Overwrite
    public int getFrontOffsetZ() {
        return patcher$frontOffsetZ;
    }

    /**
     * @reason Avoid the modulo/abs operations
     * @author JellySquid
     */
    @Overwrite
    public EnumFacing getOpposite() {
        return VALUES[opposite];
    }

    /**
     * @reason Do not allocate an excessive number of Direction arrays
     * @author JellySquid
     */
    @Overwrite
    public static EnumFacing random(Random rand) {
        return VALUES[rand.nextInt(VALUES.length)];
    }
}
