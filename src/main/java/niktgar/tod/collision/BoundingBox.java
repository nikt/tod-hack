package niktgar.tod.collision;

import niktgar.tod.geometry.Vector;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class BoundingBox {

    private final int ulX;
    private final int ulY;
    private final int lrX;
    private final int lrY;

    public int boxHeight() {
        return lrY - ulY;
    }

    public int boxWidth() {
        return lrX - ulX;
    }

    public boolean isColliding(final BoundingBox otherBox) {
        if (lrX <= otherBox.ulX() || ulX >= otherBox.lrX() || lrY <= otherBox.ulY() || ulY >= otherBox.lrY()) {
            return false;
        }
        return true;
    }
    
    // checks if pixel pos in within box
    public boolean isInside(final Vector pos) {
        return (pos.x >= ulX && pos.x <= lrX && pos.y <= lrY && pos.y >= ulY);
    }
}
