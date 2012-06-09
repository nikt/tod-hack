package niktgar.tod.entity;

import niktgar.tod.collision.Collidable;
import niktgar.tod.geometry.Vector;
import niktgar.tod.sprite.Sprite;

import org.lwjgl.input.Keyboard;

public class PlayerEntity extends Entity {

    private final double acceleration = 0.05;
    private boolean jumping;

    public PlayerEntity(Sprite sprite) {
        super(sprite);
        jumping = true;
        position = new Vector(300, 300);
    }

    @Override
    public void doLogic() {

    }

    @Override
    public void update(long delta) {

        if (jumping) {
            velocity.y += (delta / 4) * acceleration;
            position.y += 1 * velocity.y * (delta / 2);
        }

        // keyboard input
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            // move right
            position.x += 1 * (delta / 2);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            // move left
            position.x -= 1 * (delta / 2);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            // jump
            if (!jumping) {
                jumping = true;
                velocity.y = -2;
                position.y += 1 * velocity.y * (delta / 2);
            }
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            // attack
            //
        }

        if (position.y > 600 - sprite.height()) {
            position.y = 600 - sprite.height();
            jumping = false;
        }

        if (position.x < -sprite.width()) {
            position.x = 800;
        } else if (position.x > 800) {
            position.x = -sprite.width();
        }
    }

    @Override
    public void collidedTop(Collidable collidable) {
        position.y = collidable.bound().lrY();
        velocity.y = 0;
    }

    @Override
    public void collidedBottom(Collidable collidable) {
        position.y = collidable.bound().ulY() - sprite.height();
        jumping = false;
    }
}
