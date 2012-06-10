package niktgar.tod.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import niktgar.tod.collision.BoundingBox;
import niktgar.tod.collision.Collidable;
import niktgar.tod.entity.Entity;
import niktgar.tod.entity.MovementState;
import niktgar.tod.sprite.Sprite;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
public class DefaultBlock implements Block, Collidable {

    private final Sprite sprite;
    private int x;
    private int y;

    @Override
    public void draw() {
        sprite.draw(x, y);
    }

    @Override
    public void draw(final int offsetX, final int offsetY) {
        sprite.draw(x + offsetX, y + offsetY);
    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(x, y, x + sprite.width(), y + sprite.height());
    }

    @Override
    public void collidedWith(Collidable collidable) {
        // TODO Block collision logic
    }

    @Override
    public void updateMovementState(Entity entity) {
        // change movementState of entity
        entity.movementState = MovementState.DEFAULT;
    }

    @Override
    public void update(long delta) {
        // TODO Auto-generated method stub

    }
}
