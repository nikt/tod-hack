package niktgar.tod.level.block;

import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class BlockMapFileLoaderPair {

    private final Map<Integer, BlockReference> blockIdMapping;
    private final int[][] blockIdMap;
}
