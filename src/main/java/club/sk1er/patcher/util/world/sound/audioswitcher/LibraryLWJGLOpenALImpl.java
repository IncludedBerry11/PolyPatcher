package club.sk1er.patcher.util.world.sound.audioswitcher;

import club.sk1er.patcher.Patcher;
import club.sk1er.patcher.config.PatcherConfig;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;

import java.util.List;

@SuppressWarnings("unused")
public class LibraryLWJGLOpenALImpl {
    public static void createAL() throws LWJGLException {
        try {
            if (AL.isCreated()) AL.destroy();
            AL.create();
        } catch (Exception e) {
            Patcher.instance.getLogger().error("Failed to create device, using system default.", e);
            AL.destroy();
            AL.create();
        }
    }
}
