package niktgar.tod.level;

public class MapLoader {

    public int[][] createTestMap() {
        final int[][] map = new int[100][18];

        // ground
        for (int column = 0; column < map.length; column++) {
            map[column][16] = 4;
        }

        // start steps
        for (int column = 0; column < 10; column++) {
            map[column][15] = 4;
        }

        for (int column = 0; column < 8; column++) {
            map[column][13] = 4;
            map[column][14] = 4;
        }

        // hovering platform slow with hook
        for (int column = 20; column < 35; column++) {
            map[column][13] = 4;
        }
        for (int c = 35; c < 40; c++) {
            map[c][13] = 3;
        }
        map[39][14] = 4;
        map[40][14] = 4;

        // first wall
        map[44][15] = 4;
        map[44][14] = 4;
        map[44][13] = 4;
        map[44][12] = 4;
        map[44][11] = 4;
        // hole
        map[43][16] = 0;
        map[42][16] = 0;
        // steps
        map[45][15] = 4;
        map[45][14] = 4;
        map[45][13] = 4;
        map[45][12] = 4;
        // step
        map[46][15] = 4;
        map[46][14] = 4;
        map[46][13] = 4;
        // step
        map[47][15] = 4;
        map[47][14] = 4;
        // step
        map[48][15] = 4;

        // colums
        map[52][15] = 4;
        map[52][14] = 2;
        // 2
        map[54][15] = 4;
        map[54][14] = 4;
        map[54][13] = 4;
        map[54][12] = 4;
        map[54][11] = 2;
        // 3
        map[57][15] = 4;
        map[57][14] = 4;
        map[57][13] = 4;
        map[57][12] = 4;
        map[57][11] = 4;
        map[57][10] = 2;

        // runway
        for (int c = 62; c < 84; c++) {
            map[c][15] = 2;
        }
        map[75][15] = 0;
        map[75][16] = 0;
        map[76][15] = 0;
        map[76][16] = 0;
        map[77][15] = 0;
        map[77][16] = 0;

        // jumppad
        map[90][15] = 4;
        map[91][15] = 4;
        map[92][15] = 1;
        map[93][15] = 1;
        map[93][15] = 1;
        map[94][15] = 5;
        map[95][15] = 1;

        // finalobstacle
        for (int r = 15; r > 8; r--) {
            map[96][r] = 4;
        }

        return map;
    }
}
