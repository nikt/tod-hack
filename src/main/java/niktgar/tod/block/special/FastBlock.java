package niktgar.tod.block.special;

import niktgar.tod.block.DefaultBlock;
import niktgar.tod.entity.Entity;
import niktgar.tod.entity.MovementState;
import niktgar.tod.sprite.Sprite;


public class FastBlock extends DefaultBlock {
    public FastBlock(Sprite sprite, int x, int y) {
        super(sprite, x, y);
    }
    
    @Override
    public void updateMovementState(Entity entity) {
        // change movementState of entity
        entity.movementState = MovementState.FAST;
    }
}
