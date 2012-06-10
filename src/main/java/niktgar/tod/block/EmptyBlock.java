package niktgar.tod.block;

import niktgar.tod.sprite.Sprite;

public class EmptyBlock extends Block {

    public EmptyBlock(Sprite sprite, int x, int y) {
        super(sprite, x, y);
    }

    @Override
    public void draw() {

    }

    @Override
    public void draw(int x, int y) {

    }
}
