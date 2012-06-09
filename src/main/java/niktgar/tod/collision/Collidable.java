package niktgar.tod.collision;

public interface Collidable {

    public BoundingBox bound();

    public void collidedWith(final Collidable collidable);
}
