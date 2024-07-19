package club.sk1er.patcher.asm;

import club.sk1er.patcher.tweaker.transform.OmniTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class GenericEnumDirectAccessTransformer extends OmniTransformer {
    @Override
    protected boolean tryTransform(ClassNode classNode, String name) {
        if ((classNode.access & Opcodes.ACC_ENUM) == 0) {
            return false;
        }
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("values") && method.desc.startsWith("()L")) {
                InsnList insns = new InsnList();
                insns.add(new FieldInsnNode(Opcodes.GETSTATIC, classNode.name, "$VALUES", "[L" + classNode.name + ";"));
                insns.add(new InsnNode(Opcodes.ARETURN));
                method.instructions = insns;
                break;
            }
        }
        return true;
    }
}
