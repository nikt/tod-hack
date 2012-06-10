package niktgar.tod.block;

import niktgar.tod.sprite.animation.Animation;

public class AnimatedBlock extends DefaultBlock {

    private final Animation animation;

    public AnimatedBlock(final Animation animation, int x, int y) {
        super(x, y);
        this.animation = animation;
    }

    @Override
    public void update(long delta) {
        animation.update(delta);

    }

    @Override
    public void draw() {
        animation.draw(x, y);

    }

    @Override
    public void draw(int offsetX, int offsetY) {
        animation.draw(x + offsetX, y + offsetY);
    }
}
