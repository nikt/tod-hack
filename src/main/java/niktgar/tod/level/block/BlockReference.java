package niktgar.tod.level.block;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class BlockReference {

    private final int id;
    private final String spriteName;
    private final String className;
}
