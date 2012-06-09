package niktgar.tod.block;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class BlockMap {

    public static final BlockMap EIGHT_BY_SIX = new BlockMap(new Block[25][18]);
    public static final int BLOCK_SIZE = 32;

    private final Block[][] map;
    private final int rows;
    private final int columns;

    public BlockMap(final Block[][] map) {
        this.map = map;
        this.rows = map.length;
        this.columns = map[0].length;
    }

    public void putBlock(final int row, final int column, final Block block) {
        map[row][column] = block;
    }

    public void draw() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                map[r][c].draw();
            }
        }
    }
}
