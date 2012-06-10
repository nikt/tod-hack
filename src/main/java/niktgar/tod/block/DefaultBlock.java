package niktgar.tod.block;

import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.collision.BoundingBox;
import niktgar.tod.collision.Collidable;
import niktgar.tod.entity.Entity;
import niktgar.tod.entity.MovementState;

@Accessors(fluent = true)
@Data
public abstract class DefaultBlock implements Block, Collidable {

    public static final int BLOCK_WIDTH = 32;
    public static final int BLOCK_HEIGHT = 32;

    protected int x;
    protected int y;
    protected BoundingBox bound;

    public DefaultBlock(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.bound = new BoundingBox(x, y, x + BLOCK_WIDTH, y + BLOCK_HEIGHT);
    }

    @Override
    public BoundingBox bound() {
        return bound;
    }

    @Override
    public void collidedWith(Collidable collidable) {
        // TODO Block collision logic
    }

    @Override
    public void collidedWith(Entity entity) {
        entity.movementState = MovementState.DEFAULT;
    }
}
