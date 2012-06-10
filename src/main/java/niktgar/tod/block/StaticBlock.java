package niktgar.tod.block;

import niktgar.tod.collision.BoundingBox;
import niktgar.tod.sprite.Sprite;

public class StaticBlock extends DefaultBlock {

    private final Sprite sprite;

    public StaticBlock(final Sprite sprite, final int x, final int y) {
        super(x, y);
        this.sprite = sprite;

    }

    @Override
    public void update(final long delta) {
        // Do nothing
    }

    @Override
    public void draw() {
        sprite.draw(x, y);
    }

    @Override
    public void draw(final int offsetX, final int offsetY) {
        sprite.draw(x + offsetX, y + offsetY);
    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(x, y, x + sprite.width(), y + sprite.height());
    }
}
