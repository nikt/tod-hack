package niktgar.tod.block;

import niktgar.tod.collision.BoundingBox;
import niktgar.tod.collision.Collidable;
import niktgar.tod.entity.Entity;

public interface Block {

    public void draw();

    public void draw(final int offsetX, final int offsetY);

    public BoundingBox bound();

    public void collidedWith(Collidable collidable);

    public void updateMovementState(Entity entity);

    public void update(final long delta);

}