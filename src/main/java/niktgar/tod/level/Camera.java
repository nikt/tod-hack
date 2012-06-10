package niktgar.tod.level;

import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.entity.Entity;

@Accessors(fluent = true)
@Data
public class Camera {

    public static final int DEFAULT_LEFT_SOFT_BOUNDARY = 300;
    public static final int DEFAULT_RIGHT_SOFT_BOUNDARY = 500;

    public static final int DEFAULT_LEFT_BOUNDARY = 0;
    public static final int DEFAULT_RIGHT_BOUNDARY = 800;

    private Entity anchor;
    private int x;
    private int leftSoftBoundary;
    private int rightSoftBoundary;
    private int leftBoundary;
    private int rightBoundary;

    private boolean isFrozen;

    public Camera(Entity anchor) {
        this.anchor = anchor;
        x = 0;
        leftSoftBoundary = DEFAULT_LEFT_SOFT_BOUNDARY;
        rightSoftBoundary = DEFAULT_RIGHT_SOFT_BOUNDARY;
        leftBoundary = DEFAULT_LEFT_BOUNDARY;
        rightBoundary = DEFAULT_RIGHT_BOUNDARY;
        isFrozen = false;
    }

    public void pull() {
        if (!isFrozen) {
            final int offset = anchor.position().snappedX() - x;
            if (offset < leftSoftBoundary) {
                x -= leftSoftBoundary - offset;
                if (x < leftBoundary) {
                    x = leftBoundary;
                }
            } else if (offset > rightSoftBoundary) {
                x += offset - rightSoftBoundary;
                if (x > rightBoundary) {
                    x = rightBoundary;
                }
            }
        }
    }
}
