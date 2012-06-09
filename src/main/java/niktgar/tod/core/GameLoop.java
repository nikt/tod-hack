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

import niktgar.tod.block.BlockLayer;
import niktgar.tod.block.BlockMap;
import niktgar.tod.block.BlockMapBuilder;
import niktgar.tod.entity.PlayerEntity;
import niktgar.tod.sprite.Sprite;
import niktgar.tod.sprite.SpriteLoader;
import niktgar.tod.sprite.TextureLoader;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameLoop {

    private static long timerTicksPerSecond = Sys.getTimerResolution();

    private final int[][] testMap;

    private final Dimension windowDimensions = new Dimension(800, 600);

    private final TextureLoader textureLoader;
    private final SpriteLoader spriteLoader;

    private Sprite playerSprite;

    private final BlockMapBuilder mapBuilder;
    private final BlockLayer currentBlockLayer;
    private final BlockMap currentBlockMap;

    private PlayerEntity player;

    private long time;
    private long elapsed;

    public GameLoop() throws TODException {
        textureLoader = new TextureLoader();
        spriteLoader = new SpriteLoader(textureLoader);
        mapBuilder = new BlockMapBuilder(spriteLoader);

        initialize();

        testMap = new int[25][18];
        for (int column = 0; column < testMap.length; column++) {
            testMap[column][16] = 4;
        }

        currentBlockLayer = new BlockLayer();
        currentBlockMap = mapBuilder.buildBlockMap(testMap, currentBlockLayer);
    }

    public void initialize() {
        try {
            Display.setTitle("TOD");
            Display.setFullscreen(false);
            Display.setDisplayMode(new DisplayMode(windowDimensions.width, windowDimensions.height));
            Display.create();
            glInitialization();

            playerSprite = spriteLoader.loadSprite("entities/angry_tree.png");
            player = new PlayerEntity(playerSprite);

            time = (Sys.getTime() * 1000) / timerTicksPerSecond;
            elapsed = 0;
        } catch (LWJGLException e) {
            throw new RuntimeException("Game initialization failed");
        } catch (TODException e) {
            throw new RuntimeException("Failed to load sprite");
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

    public void run() {
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();

            elapsed = ((Sys.getTime() * 1000) / timerTicksPerSecond) - time;
            time = (Sys.getTime() * 1000) / timerTicksPerSecond;

            // call updates
            update(elapsed);
        }
        Display.destroy();
    }

    public void update(long delta) {
        Display.sync(60);

        currentBlockMap.draw();

        currentBlockLayer.collide(player);
        player.update(delta);

        // has to go after all other drawing
        Display.update();
    }
}
