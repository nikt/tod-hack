package niktgar.tod.block;

import java.util.ArrayList;

import niktgar.tod.collision.Intersection;
import niktgar.tod.entity.Entity;

public class BlockLayer extends ArrayList<Block> {

    private static final long serialVersionUID = 1L;

    public BlockLayer() {
        super();
    }

    public void collide(final Entity entity) {
        System.out.println("CHECK");
        for (final Block block : this) {

            if (Intersection.checkForCollision(block.bound(), entity.bound())) {
                System.err.println("COLLISION");
                entity.collidedWith(block);
            }
        }
    }
}
