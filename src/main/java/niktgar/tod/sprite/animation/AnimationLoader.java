package niktgar.tod.sprite.animation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import niktgar.tod.core.TODException;
import niktgar.tod.sprite.Sprite;
import niktgar.tod.sprite.SpriteLoader;
import niktgar.tod.sprite.TextureLoader;

public class AnimationLoader {

    private final TextureLoader textureLoader;
    private final SpriteLoader spriteLoader;

    static final String resourceString = "src/main/resources/";
    static final String ext = ".png";

    public AnimationLoader() {
        textureLoader = new TextureLoader();
        spriteLoader = new SpriteLoader(textureLoader);
    }
    
    public Animation loadAnimation(String animationName) throws TODException {
        List<Sprite> sprites = new ArrayList<Sprite>();

        File file = new File(resourceString + animationName + 1 + ext);
        for (int i = 1; file.exists(); file = new File(resourceString + animationName + (++i) + ext)) {
            sprites.add(spriteLoader.loadSprite(animationName + i + ext));
        }

        if (sprites.isEmpty()) {
            throw new RuntimeException("No animation of that name found.");
        }

        return new Animation(sprites);
    }

    public Animation loadMaskedAnimation(String animationName) throws TODException {
        List<Sprite> sprites = new ArrayList<Sprite>();

        File file = new File(resourceString + animationName + 1 + ext);
        for (int i = 1; file.exists(); file = new File(resourceString + animationName + (++i) + ext)) {
            sprites.add(spriteLoader.loadMaskedSprite(animationName + i + ext, animationName + i + "-mask" + ext));
        }

        if (sprites.isEmpty()) {
            throw new RuntimeException("No animation of that name found.");
        }

        return new Animation(sprites);
    }
}
