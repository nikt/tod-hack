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
import java.util.ArrayList;
import java.util.List;

import niktgar.tod.block.BlockLayer;
import niktgar.tod.block.BlockMap;
import niktgar.tod.block.BlockMapBuilder;
import niktgar.tod.block.MapLoader;
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

    private final Dimension windowDimensions = new Dimension(800, 600);

    private final TextureLoader textureLoader;
    private final SpriteLoader spriteLoader;

    private final MapLoader mapLoader;
    private final BlockMapBuilder mapBuilder;
    private final BlockLayer currentBlockLayer;
    private final BlockMap currentBlockMap;

    private Sprite background;

    private PlayerEntity player;

    private long time;
    private long elapsed;

    public GameLoop() throws TODException {
        textureLoader = new TextureLoader();
        spriteLoader = new SpriteLoader(textureLoader);
        mapLoader = new MapLoader();
        mapBuilder = new BlockMapBuilder(spriteLoader);

        initialize();

        currentBlockLayer = new BlockLayer();
        currentBlockMap = mapBuilder.buildBlockMap(mapLoader.createTestMap(), currentBlockLayer);
    }

    public void initialize() throws TODException {
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

            List<Sprite> sprites = new ArrayList<Sprite>();
            sprites.add(spriteLoader.loadSprite("entities/angry_tree.png"));
            sprites.add(spriteLoader.loadSprite("entities/angry_tree2.png"));
            player = new PlayerEntity(sprites);

            background = spriteLoader.loadSprite("forest-light-900.jpg");

            time = (Sys.getTime() * 1000) / timerTicksPerSecond;
            elapsed = 0;
        } catch (LWJGLException e) {
            throw new RuntimeException("Game initialization failed");
        }
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
            // call collisions
            handleCollisions();
            // call drawing
            draw();
            // switch display buffer in
            Display.update();
        }
        Display.destroy();
    }

    public void handleCollisions() {
        currentBlockLayer.checkForCollisions(player);
    }

    public void update(long delta) {
        Display.sync(60);
        player.update(delta);
    }

    public void draw() {
        background.draw(0, 0);
        currentBlockMap.draw();
        player.draw();
    }
}
