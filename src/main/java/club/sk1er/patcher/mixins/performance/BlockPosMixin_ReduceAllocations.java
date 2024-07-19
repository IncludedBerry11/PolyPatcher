package club.sk1er.patcher.mixins.performance;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockPos.class)
public abstract class BlockPosMixin_ReduceAllocations extends Vec3i {
    public BlockPosMixin_ReduceAllocations(int i, int j, int k) {
        super(i, j, k);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos up() {
        return new BlockPos(x, y + 1, z);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos up(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(x, y + offset, z);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos down() {
        return new BlockPos(x, y - 1, z);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos down(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(x, y - offset, y);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos north() {
        return new BlockPos(x, y, z - 1);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos north(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(x, y, z - offset);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos south() {
        return new BlockPos(x, y, z + 1);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos south(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(x, y, z + offset);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos west() {
        return new BlockPos(x - 1, y, z);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos west(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(x - offset, y, z);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos east() {
        return new BlockPos(x + 1, y, z);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos east(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(x + offset, y, z);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos offset(EnumFacing direction) {
        return new BlockPos(x + direction.getFrontOffsetX(), y + direction.getFrontOffsetY(), z + direction.getFrontOffsetZ());
    }
}
