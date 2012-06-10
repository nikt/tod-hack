package niktgar.tod.core;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import org.lwjgl.Sys;

@Accessors(fluent = true)
@Data
public class Timer {

    public static final long ticksPerSecond = Sys.getTimerResolution();

    @Setter(AccessLevel.NONE)
    private long start;
    @Setter(AccessLevel.NONE)
    private long elapsed;
    @Setter(AccessLevel.NONE)
    private long delta;

    public Timer() {
        reset();
    }

    public void reset() {
        start = currentTimeInMilliseconds();
        elapsed = 0;
        delta = 0;
    }

    public long tick() {
        final long oldElapsed = elapsed;
        elapsed = currentTimeInMilliseconds() - start;
        delta = elapsed - oldElapsed;
        return delta;
    }

    private long currentTimeInMilliseconds() {
        return (Sys.getTime() * 1000) / ticksPerSecond;
    }
}