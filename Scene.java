import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Class that defines our Scene made up of a Checkerboard and Marble
 * @author Lou Brand
 */
 
public class Scene{
    private Checkerboard myCheckerboard;
    private Marble myMarble;
    private RayTracer myRayTracer;
    private BufferedImage myImage;
    private double[] eye;
    
    /**
     * Constructor initializes a Checkerboard, Marble, BufferedImage and a RayTracer
     */
    public Scene(int width, int height){
        myCheckerboard = new Checkerboard();
        myMarble = new Marble();
        myImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        eye = new double[]{0, -300, 1000};
        myRayTracer = new RayTracer(this);                           
    }
    
    /**
     * Translates and scales each object accordingly and renders the scene
     */
    public BufferedImage bufferScene(){
        Graphics2D g2d = (Graphics2D) myImage.getGraphics();
        
        double[][] scaleCheckerboard = scale(5000);
        double[][] moveCheckerboard = translate(0, 0, 0);
        double[][] checkerboardTransform = Matrix.multiply(moveCheckerboard, scaleCheckerboard);
        
        double[][] scaleMarble = scale(200);
        double[][] raiseMarble = translate(0, -200, 0);
        double[][] marbleTransform = Matrix.multiply(raiseMarble, scaleMarble);
        
        myCheckerboard.prepare(checkerboardTransform);
        myMarble.prepare(marbleTransform);

        myImage = myRayTracer.render();
        
        return myImage;
    }
    
    /**
     * Gets the current rendered image associated with this Scene
     */
    public BufferedImage getImage(){
        return myImage;
    }
    
    /**
     * Creates a scaling transformation matrix
     */
    public double[][] scale(double s){
        double[][] scaler = new double[][]{{s, 0, 0, 0},
                                           {0, s, 0, 0},
                                           {0, 0, s, 0},
                                           {0, 0, 0, 1}};
        return scaler;
    }
    
    /**
     * Creates a translating transformation matrix
     */
    public double[][] translate(double x, double y, double z){
        double[][] translater = new double[][]{{1, 0, 0, x},
                                               {0, 1, 0, y},
                                               {0, 0, 1, z},
                                               {0, 0, 0, 1}};
        return translater;
    }
    
    /**
     * Gets the eye location associated with this Scene
     */
    public double[] getEye(){
        return eye;
    }
    
    /**
     * Gets the Checkerboard associated with this Scene
     */
    public Checkerboard getCheckerboard(){
        return myCheckerboard;
    }
    
    /**
     * Gets the Marble associated with this Scene
     */
    public Marble getMarble(){
        return myMarble;
    }
}
