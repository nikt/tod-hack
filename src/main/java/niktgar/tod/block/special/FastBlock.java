package niktgar.tod.block.special;

import niktgar.tod.block.StaticBlock;
import niktgar.tod.entity.Entity;
import niktgar.tod.entity.MovementState;
import niktgar.tod.sprite.Sprite;

public class FastBlock extends StaticBlock {

    public FastBlock(final Sprite sprite, final int x, final int y) {
        super(sprite, x, y);
    }

    @Override
    public void collidedWith(final Entity entity) {
        entity.movementState = MovementState.FAST;
    }
}
