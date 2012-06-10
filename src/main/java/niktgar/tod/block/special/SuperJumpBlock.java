package niktgar.tod.block.special;

import niktgar.tod.block.StaticBlock;
import niktgar.tod.entity.Entity;
import niktgar.tod.entity.MovementState;
import niktgar.tod.sprite.Sprite;

public class SuperJumpBlock extends StaticBlock {

    public SuperJumpBlock(final Sprite sprite, int x, int y) {
        super(sprite, x, y);
    }

    @Override
    public void collidedWith(Entity entity) {
        entity.movementState = MovementState.SUPER_JUMP;
    }
}