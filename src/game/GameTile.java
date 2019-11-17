package game;

import java.awt.*;

public interface GameTile {
    public ThreadLocal<Image> picture = new ThreadLocal<Image>();
}
