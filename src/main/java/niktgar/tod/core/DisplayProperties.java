package niktgar.tod.core;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class DisplayProperties {

    private final int width;
    private final int height;
    private final boolean isFullscreen;
    private final String title;
}
