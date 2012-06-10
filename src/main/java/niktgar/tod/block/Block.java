package niktgar.tod.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.collision.BoundingBox;
import niktgar.tod.collision.Collidable;
import niktgar.tod.sprite.Sprite;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
public class Block implements Collidable {

    private final Sprite sprite;
    private int x;
    private int y;

    public void draw() {
        sprite.draw(x, y);
    }

    public void draw(final int offsetX, final int offsetY) {
        sprite.draw(x + offsetX, y + offsetY);
    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(x, y, x + sprite.width(), y + sprite.height());
    }

    @Override
    public void collidedWith(Collidable collidable) {
        // TODO Block collision logic
    }
}
