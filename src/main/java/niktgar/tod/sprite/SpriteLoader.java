package niktgar.tod.sprite;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class SpriteLoader {

    private TextureLoader textureLoader;

    public Sprite loadSprite(final String file) {
        return null;
    }
}
