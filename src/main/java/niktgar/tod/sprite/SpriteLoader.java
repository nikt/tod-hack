package niktgar.tod.sprite;

import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.core.TODException;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
public class SpriteLoader {

    private TextureLoader textureLoader;

    public Sprite loadSprite(final String file) throws TODException {
        try {
            final Texture texture = textureLoader.getTexture(file);
            return new Sprite(texture, texture.getImageWidth(), texture.getImageHeight());
        } catch (IOException ioe) {
            throw new TODException("Failed to load texture");
        }
    }
}
