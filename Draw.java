import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * Main method that draws a Marble and Checkerboard to the screen
 * @author Lou Brand
 */
 
public class Draw{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Marble on Checkerboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Canvas myCanvas = new Canvas();
        Container cp = frame.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(myCanvas, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        
        //myCanvas.playAnimation();
    }
}
