package niktgar.tod.block;

public class EmptyBlock extends DefaultBlock {

    public EmptyBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(long delta) {}

    @Override
    public void draw() {}

    @Override
    public void draw(int offsetX, int offsetY) {}
}
