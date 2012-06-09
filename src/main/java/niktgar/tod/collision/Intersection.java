package niktgar.tod.collision;

public class Intersection {

    public static boolean checkForCollision(final BoundingBox a, final BoundingBox b) {
        if (a.lrX() <= b.ulX() || a.ulX() >= b.lrX() || a.lrY() >= b.ulY() || a.ulY() <= b.lrY()) {
            return false;
        }
        return true;
    }
}
