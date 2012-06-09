package niktgar.tod.entity;

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
    public void update(long delta) {

        if (jumping) {
            velocity.y += (delta / 2) * acceleration;
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
                velocity.y = -4;
                position.y += 1 * velocity.y * (delta / 2);
            }
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            // attack
            //
        }

        if (position.y > 475) {
            position.y = 475;
            jumping = false;
        }
        
        if (position.x < -sprite.width())
        {
            position.x = 800;
        }
        else if (position.x > 800)
        {
            position.x = -sprite.width();
        }

        draw();
    }
}
