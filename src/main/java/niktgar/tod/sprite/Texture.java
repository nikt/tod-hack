package niktgar.tod.sprite;

import static org.lwjgl.opengl.GL11.glBindTexture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Texture {

    private final int glTarget;
    private final int id;
    //
    private int imageWidth;
    private int imageHeight;
    //
    private int textureWidth;
    private int textureHeight;
    //
    private float widthRatio;
    private float heightRatio;

    public void bind() {
        glBindTexture(glTarget, id);
    }

    public void setImageHeight(int height) {
        this.imageHeight = height;
        setHeight();
    }

    public void setImageWidth(int width) {
        this.imageWidth = width;
        setWidth();
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public float getWidth() {
        return widthRatio;
    }

    public float getHeight() {
        return heightRatio;
    }

    public void setTextureHeight(int texHeight) {
        this.textureHeight = texHeight;
        setHeight();
    }

    public void setTextureWidth(int texWidth) {
        this.textureWidth = texWidth;
        setWidth();
    }

    private void setHeight() {
        if (textureHeight != 0) {
            heightRatio = ((float) imageHeight) / textureHeight;
        }
    }

    private void setWidth() {
        if (textureWidth != 0) {
            widthRatio = ((float) imageWidth) / textureWidth;
        }
    }
}
