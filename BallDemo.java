import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
     * @param numBalls  The number of balls to create inside the box.
     * @param boxX      The x-coordinate of the top-left corner of the box.
     * @param boxY      The y-coordinate of the top-left corner of the box.
     * @param boxWidth  The width of the box.
     * @param boxHeight The height of the box.
     */
    public void boxBounce(int numBalls, int boxX, int boxY, int boxWidth, int boxHeight) {
        // Validate parameters
        if (boxX < 0 || boxY < 0 || boxWidth <= 0 || boxHeight <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Invalid box position or size. Please ensure:\n"
                    + "- boxX and boxY are non-negative.\n"
                    + "- boxWidth and boxHeight are positive values.",
                    "Parameter Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (boxX + boxWidth > myCanvas.getSize().width || boxY + boxHeight > myCanvas.getSize().height) {
            JOptionPane.showMessageDialog(null,
                    "Box dimensions exceed canvas boundaries. Please ensure:\n"
                    + "- boxWidth + boxX does not exceed canvas width.\n"
                    + "- boxHeight + boxY does not exceed canvas height.",
                    "Parameter Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (numBalls <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Number of balls must be positive. Please enter a value greater than zero.",
                    "Parameter Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        myCanvas.setVisible(true);

        // Draw the box
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(boxX, boxY, boxX + boxWidth, boxY); // Top border
        myCanvas.drawLine(boxX, boxY, boxX, boxY + boxHeight); // Left border
        myCanvas.drawLine(boxX + boxWidth, boxY, boxX + boxWidth, boxY + boxHeight); // Right border
        myCanvas.drawLine(boxX, boxY + boxHeight, boxX + boxWidth, boxY + boxHeight); // Bottom border

        // Create balls inside the box
        List<BoxBall> balls = new ArrayList<>();
        for (int i = 0; i < numBalls; i++) {
            balls.add(new BoxBall(boxX, boxY, boxWidth, boxHeight, myCanvas));
        }

        // Animate balls inside the box
        boolean finished = false;
        while (!finished) {
            myCanvas.wait(50); // Small delay for animation
            for (BoxBall ball : balls) {
                ball.move();
            }
        }
    }
}
