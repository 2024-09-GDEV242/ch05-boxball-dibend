import java.awt.Color;
import java.util.Random;

/**
 * Represents a ball that moves inside a rectangular box and bounces off its walls and other balls.
 * 
 * @version 2024-10-22
 * @author David DiBenedetto
 */
public class BoxBall {
    private int x, y;       // Position of the ball
    private int diameter;   // Diameter of the ball
    private int radius;     // Radius of the ball
    private int xSpeed, ySpeed; // Speed in x and y directions
    private Color color;    // Color of the ball
    private Canvas canvas;
    private int xLeftBound, yTopBound, boxWidth, boxHeight; // Box bounds

    private static final int SPEED_BOUND = 7;

    /**
     * Constructor to initialize the BoxBall inside the box.
     * 
     * @param xLeftBound The left bound of the box.
     * @param yTopBound The top bound of the box.
     * @param boxWidth The width of the box.
     * @param boxHeight The height of the box.
     * @param canvas The canvas to draw the ball on.
     */
    public BoxBall(int xLeftBound, int yTopBound, int boxWidth, int boxHeight, Canvas canvas) {
        Random rand = new Random();
    }
}
