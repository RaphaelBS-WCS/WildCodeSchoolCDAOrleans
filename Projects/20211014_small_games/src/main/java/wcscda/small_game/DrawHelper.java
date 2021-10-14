package wcscda.small_game;

import java.awt.geom.Ellipse2D;

public abstract class DrawHelper {
    public static Ellipse2D.Double getCircle(double centerX, double centerY, int radius) {
        return new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }
}
