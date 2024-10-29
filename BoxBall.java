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
        
        this.diameter = rand.nextInt(20) + 10; // Random diameter between 10 and 30
        this.x = rand.nextInt(boxWidth - diameter) + xLeftBound;
        this.y = rand.nextInt(boxHeight - diameter) + yTopBound;

        // Ensure non-zero, non-vertical, and non-horizontal speed
        do {
            this.xSpeed = rand.nextInt(SPEED_BOUND * 2) - SPEED_BOUND;
            this.ySpeed = rand.nextInt(SPEED_BOUND * 2) - SPEED_BOUND;
        } while (xSpeed == 0 || ySpeed == 0 || xSpeed == SPEED_BOUND || ySpeed == SPEED_BOUND);

        this.color = new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200)); // Random color, avoiding white

        this.xLeftBound = xLeftBound;
        this.yTopBound = yTopBound;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.canvas = canvas;
    }
    
    /**
     * Draw the ball at its current position.
     */
    public void draw() {
        canvas.setForegroundColor(color);
        canvas.fillCircle(x, y, diameter);
    }
    
    /**
     * Erase the ball from the canvas.
     */
    public void erase() {
        canvas.eraseCircle(x, y, diameter);
    }
    
    /**
     * Get the x-position of the ball.
     * 
     * @return The x-coordinate of the ball's position.
     */
    public int getXPosition() {
        return x;
    }

    /**
     * Get the y-position of the ball.
     * 
     * @return The y-coordinate of the ball's position.
     */
    public int getYPosition() {
        return y;
    }
    
    /**
     * Move the ball within the box, bouncing off the walls as needed.
     */
    public void move() {
        // Erase the ball at the current position
        erase();

        // Update the position
        x += xSpeed;
        y += ySpeed;

        // Check for collision with the box walls and reverse direction if necessary
        if (x <= xLeftBound || x + diameter >= xLeftBound + boxWidth) {
            xSpeed = -xSpeed;
        }
        if (y <= yTopBound || y + diameter >= yTopBound + boxHeight) {
            ySpeed = -ySpeed;
        }

        // Draw the ball at the new position
        draw();
    }
}
