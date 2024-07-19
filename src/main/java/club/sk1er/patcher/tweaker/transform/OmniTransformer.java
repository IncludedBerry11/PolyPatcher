package club.sk1er.patcher.tweaker.transform;

import org.objectweb.asm.tree.ClassNode;

/**
 * A transformer that targets every class.
 *
 * @author xtrm
 */
public abstract class OmniTransformer implements PatcherTransformer {
//    private final List<String> transformedClasses = new ArrayList<>();

    @Override
    public final void transform(ClassNode classNode, String name) {
        this.tryTransform(classNode, name);
    }

    protected abstract boolean tryTransform(ClassNode classNode, String name);

    @Override
    public final String[] getClassName() {
        return new String[0];
    }
}
