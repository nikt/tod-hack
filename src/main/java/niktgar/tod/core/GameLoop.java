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

import niktgar.tod.sprite.Sprite;
import niktgar.tod.sprite.SpriteLoader;
import niktgar.tod.sprite.TextureLoader;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameLoop {

    private final Dimension windowDimensions = new Dimension(800, 600);

    private final TextureLoader textureLoader;
    private final SpriteLoader spriteLoader;

    public GameLoop() {
        textureLoader = new TextureLoader();
        spriteLoader = new SpriteLoader(textureLoader);
        initialize();
    }

    public void initialize() {
        try {
            Display.setTitle("TOD");
            Display.setFullscreen(false);
            Display.setDisplayMode(new DisplayMode(windowDimensions.width, windowDimensions.height));
            Display.create();
            glInitialization();
        } catch (LWJGLException e) {
            throw new RuntimeException("Game initialization failed");
        }
    }

    private void glInitialization() {
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, windowDimensions.width, windowDimensions.height, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glViewport(0, 0, windowDimensions.width, windowDimensions.height);
    }

    public void run() throws TODException {
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            //
            Sprite sprite = spriteLoader.loadSprite("blocks/block_blue.png");
            sprite.draw(200, 200);
            //
            Display.update();
        }
        Display.destroy();
    }

    public void update() {
        Display.sync(60);
    }
}
