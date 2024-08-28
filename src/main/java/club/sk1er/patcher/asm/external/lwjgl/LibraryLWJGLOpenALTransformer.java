package club.sk1er.patcher.asm.external.lwjgl;

import club.sk1er.patcher.tweaker.transform.PatcherTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ListIterator;

public class LibraryLWJGLOpenALTransformer implements PatcherTransformer {
    @Override
    public String[] getClassName() {
        return new String[]{""};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
    }
}
