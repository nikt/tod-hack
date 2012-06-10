package niktgar.tod.block;

import java.util.Hashtable;

import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.core.TODException;
import niktgar.tod.sprite.SpriteLoader;

@Accessors(fluent = true)
@Data
public class BlockMapBuilder {

    private static final int BLOCK_SIZE = 32;

    private final SpriteLoader spriteLoader;
    private final Hashtable<Integer, String> blockIdMapping;

    public BlockMapBuilder(final SpriteLoader spriteLoader) {
        this.spriteLoader = spriteLoader;
        this.blockIdMapping = new Hashtable<Integer, String>();
        blockIdMapping.put(0, "empty.png");
        blockIdMapping.put(1, "hollow.png");
        blockIdMapping.put(2, "black.png");
        blockIdMapping.put(3, "red.png");
        blockIdMapping.put(4, "green.png");
    }

    public BlockMap buildBlockMap(final int[][] blockIdMap, final BlockLayer currentBlockLayer) throws TODException {
        final Block[][] blockMap = new Block[blockIdMap.length][blockIdMap[0].length];
        for (int r = 0; r < blockMap.length; r++) {
            for (int c = 0; c < blockMap[0].length; c++) {
                final int blockId = blockIdMap[r][c];
                final String blockFileString = blockIdMapping.get(blockIdMap[r][c]);
                Block block = new Block(spriteLoader.loadSprite(String.format("blocks/%s", blockFileString)), r * BLOCK_SIZE, c * BLOCK_SIZE);
                if (blockId != 0) {
                    currentBlockLayer.add(block);
                } else {
                    block = new EmptyBlock(null, r * BLOCK_SIZE, c * BLOCK_SIZE);
                }
                blockMap[r][c] = block;
            }
        }
        return new BlockMap(blockMap);
    }
}