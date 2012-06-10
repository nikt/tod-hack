package niktgar.tod.level;

import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.block.BlockLayer;
import niktgar.tod.block.BlockMap;
import niktgar.tod.entity.PlayerEntity;

@Accessors(fluent = true)
@Data
public class Level {

    private final BlockMap blockMap;
    private final BlockLayer collisionBlocks;
    private final PlayerEntity player;

    private final Camera camera;

    public Level(final BlockMap map, final BlockLayer collisionBlocks, final PlayerEntity player) {
        this.blockMap = map;
        this.collisionBlocks = collisionBlocks;
        this.player = player;
        this.camera = new Camera(player);
    }

    public void handleCollisions() {
        collisionBlocks.checkForCollisions(player);
    }

    public void update(long delta) {
        player.update(delta);
    }

    public void draw() {
        camera.pull();
        blockMap.draw(-camera.x(), 0);
        player.draw(-camera.x(), 0);
    }

}
