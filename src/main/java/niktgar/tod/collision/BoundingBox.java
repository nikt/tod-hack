package niktgar.tod.collision;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class BoundingBox {

    private final int ulX;
    private final int ulY;
    private final int lrX;
    private final int lrY;
}
