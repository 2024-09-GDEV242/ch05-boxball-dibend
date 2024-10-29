import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }

    /**
     * Draws a rectangular box on the canvas and simulates bouncing balls within it.
     * 
     * This method creates a specified number of balls within a rectangular area 
     * on the canvas, defined by the parameters for the box's position and size. 
     * Each ball moves within the box and "bounces" off the walls when it reaches 
     * the edges. The box outline is redrawn in each iteration of the animation 
     * loop to ensure that the boundaries remain clearly visible even if the balls 
     * move close to or touch the walls.
     * 
     * @param numBalls  The number of balls to create inside the box. Must be positive.
     * @param boxX      The x-coordinate of the top-left corner of the box. Must be non-negative.
     * @param boxY      The y-coordinate of the top-left corner of the box. Must be non-negative.
     * @param boxWidth  The width of the box. Must be positive and within canvas bounds.
     * @param boxHeight The height of the box. Must be positive and within canvas bounds.
     * 
     * @throws IllegalArgumentException if any parameters are out of bounds, such as:
     *                                  - numBalls is less than 1.
     *                                  - boxX or boxY are negative.
     *                                  - boxWidth or boxHeight are non-positive or exceed canvas size.
     */
    public void boxBounce(int numBalls, int boxX, int boxY, int boxWidth, int boxHeight) {
        // Validate parameters for box boundaries and number of balls
        if (numBalls < 1) {
            throw new IllegalArgumentException("Number of balls must be positive.");
        }
        if (boxX < 0 || boxY < 0 || boxWidth <= 0 || boxHeight <= 0) {
            throw new IllegalArgumentException("Box position and size must be positive and non-negative.");
        }
        if (boxX + boxWidth > myCanvas.getSize().width || boxY + boxHeight > myCanvas.getSize().height) {
            throw new IllegalArgumentException("Box dimensions exceed canvas boundaries.");
        }

        // Make the canvas visible to display the animation
        myCanvas.setVisible(true);

        // Create a list to hold the balls that will bounce within the box
        List<BoxBall> balls = new ArrayList<>();
        for (int i = 0; i < numBalls; i++) {
            balls.add(new BoxBall(boxX, boxY, boxWidth, boxHeight, myCanvas));
        }

        // Animation loop for the balls bouncing within the box
        boolean finished = false;
        while (!finished) {
            myCanvas.wait(50); // Small delay between frames to create smooth animation

            // Redraw the box on each iteration to ensure the outline remains visible
            // This prevents the balls from erasing or "wearing away" the box edges
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.drawLine(boxX, boxY, boxX + boxWidth, boxY); // Top border
            myCanvas.drawLine(boxX, boxY, boxX, boxY + boxHeight); // Left border
            myCanvas.drawLine(boxX + boxWidth, boxY, boxX + boxWidth, boxY + boxHeight); // Right border
            myCanvas.drawLine(boxX, boxY + boxHeight, boxX + boxWidth, boxY + boxHeight); // Bottom border

            // Move each ball within the box, making sure they "bounce" off the walls
            for (BoxBall ball : balls) {
                ball.move();
            }
        }
    }

}