package niktgar.tod.block;

import niktgar.tod.sprite.Sprite;

public class EmptyBlock extends DefaultBlock {

    public EmptyBlock(final Sprite sprite, final int x, final int y) {
        super(x, y);
    }

    @Override
    public void update(long delta) {}

    @Override
    public void draw() {}

    @Override
    public void draw(int offsetX, int offsetY) {}
}
