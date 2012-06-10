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

    private final Animation leftAnimation;
    private final Animation rightAnimation;

    public PlayerEntity(Animation animation) {
        super(animation);
        this.leftAnimation = null;
        this.rightAnimation = null;
        jumping = true;
        position = new Vector(300, 300);
    }

    public PlayerEntity(Animation animation, Animation leftAnimation, Animation rightAnimation) {
        super(animation);
        this.leftAnimation = leftAnimation;
        this.rightAnimation = rightAnimation;
        jumping = true;
        position = new Vector(50, 300);
    }

    @Override
    public void doLogic() {

    }

    @Override
    public void update(long delta) {
        System.out.println(position);
        
        animationState = AnimationState.IDLE;

        if (jumping) {
            velocity.y += acceleration * delta * accAdjustment;
            position.y += velocity.y * delta * velAdjustment;
        }
        
        switch (movementState) {
        case FAST :
            velocity.x = (float) 2;
            break;
        case SLOW :
            velocity.x = (float) 0.5;
            break;
        default :
            velocity.x = (float) 1;
        }

        // keyboard input
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            // move right
            position.x += velocity.x * delta / 2;
            animationState = AnimationState.RIGHT;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            // move left
            position.x -= velocity.x * delta / 2;
            animationState = AnimationState.LEFT;
        }
        if ((Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_UP)) && movementState != MovementState.NO_JUMP) {
            // jump
            if (!jumping) {
                jumping = true;
                if (movementState == MovementState.SUPER_JUMP) {
                    velocity.y = -3;
                } else {
                    velocity.y = -2;
                }
                position.y += velocity.y * delta * velAdjustment;
                //state = State.JUMP;
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

        // if (position.x < 0) {
        // position.x = 0;
        // } else if (position.x + animation.width() > 1600) {
        // position.x = 1600 - animation.width();
        // }

        // update proper animation

        switch (animationState) {
        case IDLE :
            animation.update(delta);
            break;
        case LEFT:
            if (leftAnimation != null) {
                leftAnimation.update(delta);
            } else {
                animation.update(delta);
            }
            break;
        case RIGHT:
            if (rightAnimation != null) {
                rightAnimation.update(delta);
            } else {
                animation.update(delta);
            }
            break;
        default:
            animation.update(delta);
        }
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

    @Override
    public void draw(int x, int y) {
        switch (animationState) {
        case IDLE :
            animation.draw(position.snappedX() + x, position.snappedY() + y);
            break;
        case LEFT:
            if (leftAnimation != null) {
                leftAnimation.draw(position.snappedX() + x, position.snappedY() + y);
            } else {
                animation.draw(position.snappedX() + x, position.snappedY() + y);
            }
            break;
        case RIGHT:
            if (rightAnimation != null) {
                rightAnimation.draw(position.snappedX() + x, position.snappedY() + y);
            } else {
                animation.draw(position.snappedX() + x, position.snappedY() + y);
            }
            break;
        default:
            animation.draw(position.snappedX() + x, position.snappedY() + y);
        }
    }
}
