package niktgar.tod.collision;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class BoundingBoxQuad {

    private static final int TOP_SHRINK = 10;
    private static final int LEFT_SHRINK = 10;

    private final BoundingBox top;
    private final BoundingBox left;
    private final BoundingBox right;
    private final BoundingBox bottom;

    public BoundingBoxQuad(final BoundingBox box) {
        this.top = new BoundingBox(box.ulX() + TOP_SHRINK, box.ulY(), box.lrX() - TOP_SHRINK, box.ulY() + (box.boxHeight() / 2));
        this.left = new BoundingBox(box.ulX(), box.ulY() + LEFT_SHRINK, box.ulX() + (box.boxWidth() / 2), box.lrY() - LEFT_SHRINK);
        this.right = new BoundingBox(box.ulX() + (box.boxWidth() / 2), box.ulY() + LEFT_SHRINK, box.lrX(), box.lrY() - LEFT_SHRINK);
        this.bottom = new BoundingBox(box.ulX() + TOP_SHRINK, box.ulY() + (box.boxHeight() / 2), box.lrX() - TOP_SHRINK, box.lrY());
    }
}
