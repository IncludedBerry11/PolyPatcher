package club.sk1er.patcher.asm;

import club.sk1er.patcher.tweaker.transform.OmniTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xtrm
 */
public class BlockPosDirectAccessTransformer extends OmniTransformer {
    private static final String VEC3I_CLASS = "net/minecraft/util/Vec3i";
    private static final String BLOCKPOS_CLASS = "net/minecraft/util/BlockPos";
    private static final String MUTABLEBLOCKPOS_CLASS = "net/minecraft/util/BlockPos$MutableBlockPos";

    @Override
    protected boolean tryTransform(ClassNode classNode, String name) {
        if (name.equals(VEC3I_CLASS) || name.startsWith(BLOCKPOS_CLASS) || name.startsWith(MUTABLEBLOCKPOS_CLASS)) {
            return false;
        }
        boolean found = false;
        for (MethodNode method : classNode.methods) {
            InsnList insns = method.instructions;
            Iterator<AbstractInsnNode> iterator = insns.iterator();
            Set<MethodInsnNode> toRemove = new HashSet<>();
            while (iterator.hasNext()) {
                AbstractInsnNode node = iterator.next();
                if (node.getOpcode() == Opcodes.INVOKEVIRTUAL) {
                    MethodInsnNode methodInsnNode = (MethodInsnNode) node;
                    if ((methodInsnNode.owner.equals(VEC3I_CLASS) || methodInsnNode.owner.equals(MUTABLEBLOCKPOS_CLASS)) && (methodInsnNode.name.startsWith("get") && methodInsnNode.desc.equals("()I"))) {
                        found = true;
                        toRemove.add(methodInsnNode);
                    }
                }
            }
            for (MethodInsnNode methodInsnNode : toRemove) {
                String fieldName = methodInsnNode.name.substring(3).toLowerCase();
                String owner = methodInsnNode.owner;
                if (owner.equals(MUTABLEBLOCKPOS_CLASS)) {
                    owner = VEC3I_CLASS;
                }
                FieldInsnNode fieldInsnNode = new FieldInsnNode(Opcodes.GETFIELD, owner, fieldName, "I");
                insns.set(methodInsnNode, fieldInsnNode);
            }
        }
        return found;
    }
}
