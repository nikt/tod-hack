package niktgar.tod.collision;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class BoundingBoxQuad {

    private final BoundingBox top;
    private final BoundingBox left;
    private final BoundingBox right;
    private final BoundingBox bottom;

    public BoundingBoxQuad(final BoundingBox box) {
        this.top = new BoundingBox(box.ulX() + 20, box.ulY(), box.lrX() - 20, box.lrY() - box.ulY() / 2);
        this.left = null;
        this.right = null;
        this.bottom = null;
    }
}
