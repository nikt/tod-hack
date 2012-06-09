package niktgar.tod.entity;

import niktgar.tod.collision.BoundingBox;
import niktgar.tod.collision.Collidable;
import niktgar.tod.geometry.Vector;
import niktgar.tod.sprite.Sprite;

public class Entity implements Collidable {

    protected Sprite sprite;
    protected Vector position;
    protected Vector velocity;

    public Entity(Sprite sprite) {
        this.sprite = sprite;
        initialize();
    }

    public void initialize() {
        velocity = new Vector(5, 5);
    }

    public void draw() {
        sprite.draw(position.snappedX(), position.snappedY());
    }

    public void update(long delta) {

    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(position.snappedX(), position.snappedY(), position.snappedX() + sprite.width(), position.snappedY() + sprite.height());
    }

    @Override
    public void collidedWith(Collidable collidable) {
        // collide logic
    }
}
