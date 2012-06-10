package niktgar.tod.level.block;

import java.util.Hashtable;

import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.block.Block;
import niktgar.tod.block.DefaultBlock;
import niktgar.tod.block.EmptyBlock;
import niktgar.tod.block.special.FastBlock;
import niktgar.tod.block.special.NoJumpBlock;
import niktgar.tod.block.special.SuperJumpBlock;
import niktgar.tod.core.TODException;
import niktgar.tod.sprite.Sprite;
import niktgar.tod.sprite.SpriteLoader;

@Accessors(fluent = true)
@Data
public class BlockMapBuilder {

    private static final Class[] BLOCK_CONSTRUCTOR_PARAM_CLASSES = { Sprite.class, int.class, int.class };
    private static final String FAST_BLOCK_CLASS = "niktgar.tod.block.special.FastBlock";
    private static final String NO_JUMP_BLOCK_CLASS = "niktgar.tod.block.special.NoJumpBlock";
    private static final String SLOW_BLOCK_CLASS = "niktgar.tod.block.special.SlowBlock";
    private static final String SUPER_JUMP_BLOCK_CLASS = "niktgar.tod.block.special.SuperJumpBlock";

    private static final int BLOCK_SIZE = 32;
    private static final String BLOCK_FILE_STRING_PREFIX = "blocks/%s";
    private static final String BLOCK_MASK_FILE_STRING = String.format(BLOCK_FILE_STRING_PREFIX, "_mask.gif");

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
        blockIdMapping.put(5, "blue_7.png");
    }

    public BlockMap buildBlockMap(final int[][] blockIdMap, final BlockLayer currentBlockLayer) throws TODException {
        final Block[][] blockMap = new Block[blockIdMap.length][blockIdMap[0].length];
        for (int r = 0; r < blockMap.length; r++) {
            for (int c = 0; c < blockMap[0].length; c++) {
                final int blockId = blockIdMap[r][c];
                final String blockFileString = blockIdMapping.get(blockIdMap[r][c]);
                final Sprite sprite = spriteLoader.loadMaskedSprite(String.format(BLOCK_FILE_STRING_PREFIX, blockFileString), BLOCK_MASK_FILE_STRING);
                DefaultBlock block;
                switch (blockId) {
                case 0:
                    block = new EmptyBlock(sprite, r * BLOCK_SIZE, c * BLOCK_SIZE);
                    break;
                case 1:
                    block = new NoJumpBlock(sprite, r * BLOCK_SIZE, c * BLOCK_SIZE);
                    currentBlockLayer.add(block);
                    break;
                case 2:
                    block = new FastBlock(sprite, r * BLOCK_SIZE, c * BLOCK_SIZE);
                    currentBlockLayer.add(block);
                    break;
                case 3:
                    block = null;
                    final Object[] blockConstructorParameters = { sprite, r * BLOCK_SIZE, c * BLOCK_SIZE };
                    try {
                        block = (DefaultBlock) Class.forName(SLOW_BLOCK_CLASS).getConstructor(BLOCK_CONSTRUCTOR_PARAM_CLASSES)
                                .newInstance(blockConstructorParameters);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                    // block = new SlowBlock(sprite, r * BLOCK_SIZE, c *
                    // BLOCK_SIZE);
                    currentBlockLayer.add(block);
                    break;
                case 4:
                    block = new DefaultBlock(sprite, r * BLOCK_SIZE, c * BLOCK_SIZE);
                    currentBlockLayer.add(block);
                    break;
                case 5:
                    block = new SuperJumpBlock(sprite, r * BLOCK_SIZE, c * BLOCK_SIZE);
                    currentBlockLayer.add(block);
                    break;
                default:
                    block = new DefaultBlock(sprite, r * BLOCK_SIZE, c * BLOCK_SIZE);
                    currentBlockLayer.add(block);
                }
                blockMap[r][c] = block;
            }
        }
        return new BlockMap(blockMap);
    }
}