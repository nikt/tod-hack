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
        velocity = new Vector(0, 0);
    }

    public void draw() {
        sprite.draw(position.snappedX(), position.snappedY());
    }

    public void update(long delta) {
        position.x += velocity.x / delta;
        position.y += velocity.y / delta;
    }

    public void applyForce(final long delta, final Vector vector) {
        velocity.x += vector.x / delta;
        velocity.y += vector.y / delta;
    }

    public void doLogic() {

    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(position.snappedX(), position.snappedY(), position.snappedX() + sprite.width(), position.snappedY() + sprite.height());
    }

    public void collidedTop(Collidable collidable) {

    }

    public void collidedBottom(Collidable collidable) {

    }

    public void collidedLeft(Collidable collidable) {

    }

    public void collidedRight(Collidable collidable) {

    }

    @Override
    public void collidedWith(Collidable collidable) {
        // TODO Auto-generated method stub

    }
}
