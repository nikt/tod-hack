package niktgar.tod.block;

import niktgar.tod.entity.Entity;
import niktgar.tod.entity.MovementState;
import niktgar.tod.sprite.Sprite;


public class DefaultBlock extends Block {
    
    public DefaultBlock(Sprite sprite, int x, int y) {
        super(sprite, x, y);
    }
    
    public void updateMovementState(Entity entity) {
        // change movementState of entity
        entity.movementState = MovementState.DEFAULT;
    }
}
