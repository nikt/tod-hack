package niktgar.tod.core;

public class MapLoader {

    public int[][] createTestMap() {
        final int[][] map = new int[25][18];
        for (int column = 0; column < map.length; column++) {
            map[column][16] = 4;
        }
        for (int column = 0; column < 10; column++) {
            map[column][15] = 4;
        }

        for (int column = 0; column < 8; column++) {
            map[column][13] = 4;
            map[column][14] = 4;
        }
        for (int column = 20; column < map.length; column++) {
            map[column][13] = 4;
        }
        return map;
    }
}
