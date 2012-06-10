package niktgar.tod.entity;

import niktgar.tod.collision.Collidable;
import niktgar.tod.geometry.Vector;
import niktgar.tod.sprite.Animation;

import org.lwjgl.input.Keyboard;

public class PlayerEntity extends Entity {

    private final double acceleration = 0.05;
    private boolean jumping;

    private static final double velAdjustment = 0.5;
    private static final double accAdjustment = 0.25;

    public PlayerEntity(Animation animation) {
        super(animation);
        jumping = true;
        position = new Vector(50, 300);
    }

    @Override
    public void doLogic() {

    }

    @Override
    public void update(long delta) {
        System.out.println(position);

        if (jumping) {
            velocity.y += acceleration * delta * accAdjustment;
            position.y += velocity.y * delta * velAdjustment;
        }

        // keyboard input
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            // move right
            position.x += delta / 2;
            state = State.RIGHT;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            // move left
            position.x -= delta / 2;
            state = State.LEFT;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            // jump
            if (!jumping) {
                jumping = true;
                velocity.y = -2;
                position.y += velocity.y * delta * velAdjustment;
                state = State.JUMP;
            }
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            // attack
            //
        }

        if (position.y > 600 - animation.height()) {
            position.y = 600 - animation.height();
            velocity.y = 0;
            jumping = false;
        }

        if (position.x < 0) {
            position.x = 0;
        } else if (position.x + animation.width() > 1600) {
            position.x = 1600 - animation.width();
        }

        animation.update(delta);
    }

    @Override
    public void collidedBottom(Collidable collidable) {
        super.collidedBottom(collidable);
        jumping = false;
    }

    @Override
    public void alertFloating() {
        if (!jumping) {
            velocity.y = 0;
            jumping = true;
        }
    }
}
