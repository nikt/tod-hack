package niktgar.tod.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Dimension;

import niktgar.tod.sprite.TextureLoader;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameLoop {

    private final Dimension windowDimensions = new Dimension(800, 600);

    private TextureLoader textureLoader;

    public GameLoop() {
        initialize();
    }

    public void initialize() {
        try {
            Display.setTitle("TOD");
            Display.setFullscreen(false);
            Display.setDisplayMode(new DisplayMode(windowDimensions.width, windowDimensions.height));
            Display.create();
            glEnable(GL_TEXTURE_2D);
            glDisable(GL_DEPTH_TEST);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, windowDimensions.width, windowDimensions.height, 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glViewport(0, 0, windowDimensions.width, windowDimensions.height);

            textureLoader = new TextureLoader();

        } catch (LWJGLException e) {
            throw new RuntimeException("Game initialization failed");
        }
    }

    public void run() {
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();

            Display.update();
        }
        Display.destroy();
    }

    public void update() {
        Display.sync(60);
    }
}
