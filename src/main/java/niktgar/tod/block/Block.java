package niktgar.tod.block;

import niktgar.tod.collision.BoundingBox;
import niktgar.tod.collision.Collidable;
import niktgar.tod.entity.Entity;

public interface Block extends Collidable {

    public void update(final long delta);

    public void draw();

    public void draw(final int offsetX, final int offsetY);

    @Override
    public BoundingBox bound();

    @Override
    public void collidedWith(final Collidable collidable);

    public void collidedWith(final Entity entity);
}