import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class that defines our Canvas for drawing a Scene
 * @author Lou Brand
 */
 
public class Canvas extends JPanel{
    private Scene myScene;
    private ArrayList<BufferedImage> myAnimation;
    private BufferedImage currentFrame;
    
    /**
     * Constructor creates a new JPanel of a given size and initializes the Scene
     */
    public Canvas(){
        myScene = new Scene(800, 800);
        setPreferredSize(new Dimension(800, 800));
        //myAnimation = new ArrayList<BufferedImage>();
        //bufferAnimation();
    }
    
    /**
     * Bufferes the Scene and draws it to the screen
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        myScene.bufferScene();
        g2d.drawImage(myScene.getImage(), 0, 0, this);
        //g2d.drawImage(currentFrame, 0, 0, this);
    }
    
    //Below are some unfinished animation methods (could maybe make a game like the SIGGRAPH water?)
    
    /**
     * Unfinished animation method (need to save the jpegs to disk probably)
     */
    public void bufferAnimation(){
        double step = 50;
        for (int i = 0; i < 50; i++){
            //myScene.bufferScene(-2000 + i * step);
            myAnimation.add(copyImage(myScene.getImage()));
            System.out.println(myAnimation.size());
        }
    }
    
    /**
     * Unfinished animation method
     */
    public void playAnimation(){
        for (BufferedImage bi : myAnimation){
            currentFrame = bi;
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
        }
    }
    
    /**
     * Deep copy Buffered Image (doesn't help because we cannot Serialize a BufferedImage)
     */
    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
}

