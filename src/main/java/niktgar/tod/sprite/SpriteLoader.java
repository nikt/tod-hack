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
            throw new TODException(String.format("Failed to load texture: %s", file));
        }
    }

    public Sprite loadMaskedSprite(final String file, final String maskFile) throws TODException {
        try {
            final Texture texture = textureLoader.getTexture(file);
            final Texture mask = textureLoader.getTexture(maskFile);
            return new MaskedSprite(texture, mask, texture.getImageWidth(), texture.getImageHeight());
        } catch (IOException ioe) {
            throw new TODException(String.format("Failed to load one of: %s %s", file, maskFile));
        }
    }
}
