package niktgar.tod.level.block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BlockMapFileLoader {

    private static final String BLOCK_MAP_FILE_STRING_BASE = "blockmaps/%s.blockmap";

    public BlockMapFileLoaderPair loadBlockMapFile(final String fileName) {
        final Map<Integer, BlockReference> blockIdMapping = new HashMap<Integer, BlockReference>();
        blockIdMapping.put(0, new BlockReference(0, "empty.png", BlockMapBuilder.EMPTY_BLOCK_CLASS));
        int[][] blockIdMap = null;
        boolean isMap = false;
        try {
            // final FileReader fileReader = new
            // FileReader(String.format(BLOCK_MAP_FILE_STRING_BASE, fileName));
            final URL url = BlockMapFileLoader.class.getClassLoader().getResource("blockmaps/test.blockmap");
            final FileReader fileReader = new FileReader(url.getPath());
            final BufferedReader bufferedReader = new BufferedReader(fileReader);
            int column = 0;
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                if (line.startsWith("S")) {
                    // System.err.println(line);
                    String[] sizes = line.replace("S", "").split(",");
                    isMap = true;
                    blockIdMap = new int[Integer.parseInt(sizes[0])][Integer.parseInt(sizes[1])];
                    continue;
                }
                if (!isMap) {
                    // System.out.println(line);
                    final String[] tokens = line.split(",");
                    final int id = Integer.parseInt(tokens[0]);
                    final String className = tokens[1];
                    final String spriteFileName = tokens[2];
                    blockIdMapping.put(id, new BlockReference(id, spriteFileName, className));
                } else {
                    // System.out.println(line);
                    final String[] blocks = line.split(",");
                    for (int i = 0; i < blockIdMap.length; i++) {
                        blockIdMap[i][column] = Integer.parseInt(blocks[i]);
                    }
                    column++;
                }

            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return new BlockMapFileLoaderPair(blockIdMapping, blockIdMap);
    }
}
