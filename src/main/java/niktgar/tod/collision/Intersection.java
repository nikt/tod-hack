package niktgar.tod.collision;

import niktgar.tod.geometry.Vector;

public class Intersection {

    public static boolean checkForCollision(final BoundingBox a, final BoundingBox b) {
        if (a.lrX() <= b.ulX() || a.ulX() >= b.lrX() || a.lrY() <= b.ulY() || a.ulY() >= b.lrY()) {
            return false;
        }
        return true;
    }
    
    // checks if pixel pos in within box b
    public static boolean checkForGround(final Vector pos, final BoundingBox b) {
        return (pos.x >= b.ulX() && pos.x <= b.lrX() && pos.y <= b.lrY() && pos.y >= b.ulY());
    }
}
