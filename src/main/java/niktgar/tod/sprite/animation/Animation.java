package niktgar.tod.sprite.animation;

import java.util.ArrayList;
import java.util.List;

import niktgar.tod.sprite.Sprite;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class Animation {
    private List<Sprite> sprites;
    private int frameCount;
    private int currentFrame;
    private long elapsed;
    
    private final int width;
    private final int height;
    
    public Animation(Sprite sprite) {
        this.sprites = new ArrayList<Sprite>();
        this.sprites.add(sprite);
        
        this.width = sprite.width();
        this.height = sprite.height();
        
        initialize();
    }
    
    public Animation(List<Sprite> sprites) {
        this.sprites = new ArrayList<Sprite>(sprites);
        
        this.width = sprites.get(0).width();
        this.height = sprites.get(0).height();
        
        initialize();
    }
    
    public void initialize() {
        frameCount = sprites.size();
        elapsed = 0;
        currentFrame = 0;
    }
    
    public void update(long delta) {
        elapsed += delta;
        
        if (elapsed >= 500) {
            currentFrame++;
            
            elapsed %= 500;
            currentFrame %= frameCount;
        }
    }
    
    public void draw(int x, int y) {
        sprites.get(currentFrame).draw(x, y);
    }
}
