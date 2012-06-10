package niktgar.tod.core;

public class Main {

    public static void main(String[] args) throws TODException {
        System.err.println("PREPARE TO COLLECT TALES OF DRAGONS");
        final GameLoop game = new GameLoop();
        game.initialize();
        game.loadTestLevel();
        game.run();
    }
}
