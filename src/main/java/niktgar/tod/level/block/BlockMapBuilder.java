package niktgar.tod.level.block;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.block.Block;
import niktgar.tod.block.DefaultBlock;
import niktgar.tod.core.TODException;
import niktgar.tod.sprite.Sprite;
import niktgar.tod.sprite.SpriteLoader;

@Accessors(fluent = true)
@Data
public class BlockMapBuilder {

    private static final Class<?>[] BLOCK_CONSTRUCTOR_PARAM_CLASSES = { Sprite.class, int.class, int.class };
    private static final String BLOCK_CLASS = "niktgar.tod.block.StaticBlock";
    public static final String EMPTY_BLOCK_CLASS = "niktgar.tod.block.EmptyBlock";
    private static final String FAST_BLOCK_CLASS = "niktgar.tod.block.special.FastBlock";
    private static final String NO_JUMP_BLOCK_CLASS = "niktgar.tod.block.special.NoJumpBlock";
    private static final String SLOW_BLOCK_CLASS = "niktgar.tod.block.special.SlowBlock";
    private static final String SUPER_JUMP_BLOCK_CLASS = "niktgar.tod.block.special.SuperJumpBlock";

    private static final String BLOCK_FILE_STRING_PREFIX = "blocks/%s";
    private static final String BLOCK_MASK_FILE_STRING = String.format(BLOCK_FILE_STRING_PREFIX, "_mask.gif");

    private final SpriteLoader spriteLoader;
    private final BlockMapFileLoader blockMapFileLoader;
    private final Map<Integer, BlockReference> blockIdMapping;

    public BlockMapBuilder(final SpriteLoader spriteLoader) {
        this.spriteLoader = spriteLoader;
        this.blockMapFileLoader = new BlockMapFileLoader();
        this.blockIdMapping = new HashMap<Integer, BlockReference>();
        blockIdMapping.put(0, new BlockReference(0, "empty.png", EMPTY_BLOCK_CLASS));
        blockIdMapping.put(1, new BlockReference(1, "hollow.png", NO_JUMP_BLOCK_CLASS));
        blockIdMapping.put(2, new BlockReference(2, "gray.png", FAST_BLOCK_CLASS));
        blockIdMapping.put(3, new BlockReference(3, "red.png", SLOW_BLOCK_CLASS));
        blockIdMapping.put(4, new BlockReference(4, "green.png", BLOCK_CLASS));
        blockIdMapping.put(5, new BlockReference(5, "blue.png", SUPER_JUMP_BLOCK_CLASS));
    }

    public Sprite loadBlockSprite(final String blockSpriteFileName) throws TODException {
        return spriteLoader.loadMaskedSprite(String.format(BLOCK_FILE_STRING_PREFIX, blockSpriteFileName), BLOCK_MASK_FILE_STRING);
    }

    public BlockMap buildBlockMap(final BlockLayer currentBlockLayer) throws TODException {
        final BlockMapFileLoaderPair pair = blockMapFileLoader.loadBlockMapFile("test");
        final Map<Integer, BlockReference> blockIdMapping = pair.blockIdMapping();
        final int[][] blockIdMap = pair.blockIdMap();

        final Block[][] blockMap = new Block[blockIdMap.length][blockIdMap[0].length];
        for (int r = 0; r < blockMap.length; r++) {
            for (int c = 0; c < blockMap[0].length; c++) {
                final int blockId = blockIdMap[r][c];
                final BlockReference blockReference = blockIdMapping.get(blockId);
                final Sprite sprite = loadBlockSprite(blockReference.spriteName());
                final Object[] blockConstructorParameters = { sprite, r * DefaultBlock.BLOCK_WIDTH, c * DefaultBlock.BLOCK_WIDTH };
                try {
                    final Block block = (Block) Class.forName(blockReference.className()).getConstructor(BLOCK_CONSTRUCTOR_PARAM_CLASSES)
                            .newInstance(blockConstructorParameters);
                    blockMap[r][c] = block;
                    if (blockId != 0) {
                        currentBlockLayer.add(block);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to instantiate a block of type: %s", blockReference.className()));
                }
            }
        }
        return new BlockMap(blockMap);
    }
}