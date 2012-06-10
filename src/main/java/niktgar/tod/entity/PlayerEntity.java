package niktgar.tod.entity;

import java.util.List;

import niktgar.tod.collision.Collidable;
import niktgar.tod.geometry.Vector;
import niktgar.tod.sprite.Animation;
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

    public PlayerEntity(List<Sprite> sprites) {
        super(sprites);
        jumping = true;
        position = new Vector(300, 300);
    }
    
    public PlayerEntity(Animation animation) {
        super(animation);
        jumping = true;
        position = new Vector(300, 300);
    }

    @Override
    public void doLogic() {

    }
    
    @Override
    public void update(long delta) {
        animation.update(delta);

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

        if (position.y > 600 - animation.height()) {
            position.y = 600 - animation.height();
            jumping = false;
        }

        if (position.x < -animation.width()) {
            position.x = 800;
        } else if (position.x > 800) {
            position.x = -animation.width();
        }
    }

    @Override
    public void collidedBottom(Collidable collidable) {
        super.collidedBottom(collidable);
        jumping = false;
    }
}
