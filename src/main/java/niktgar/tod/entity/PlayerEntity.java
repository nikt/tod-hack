package niktgar.tod.entity;

import niktgar.tod.geometry.Vector;
import niktgar.tod.sprite.Sprite;

import org.lwjgl.input.Keyboard;

public class PlayerEntity extends Entity {

    private final double acceleration = 9.8;
    private boolean jumping;

    public PlayerEntity(Sprite sprite) {
        super(sprite);
        jumping = false;
        position = new Vector(300, 300);
    }

    @Override
    public void update(long delta) {

        if (jumping) {
            velocity.y += delta * acceleration;
        }
        position.y += 1 * velocity.y * (delta / 2);

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
            jumping = true;
            velocity.y = 20;
            position.y -= 1 * velocity.y * (delta / 2);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            // attack
            //
        }

        if (position.y > 450) {
            position.y = 450;
            jumping = false;
        }

        draw();
    }
}
