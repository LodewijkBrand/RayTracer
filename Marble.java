import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Class that represents a Marble
 * @author Lou Brand
 */
 
public class Marble{
    private double radius, centerX, centerY, centerZ, r;
    private double[] coords;
    private double[] currentCoords;
    private double[] vert1, vert2;
    
    /** 
     * Generates a Marble centered around the origin
     * 4--------------1
     * |     *  *     |
     * |  *        *  |
     * | *          * |
     * | *          * | 
     * |  *        *  |
     * |     *  *     |
     * 3--------------2     
     */
    public Marble(){
        vert1 = new double[] {1, 1, 0, 1}; //Two vertices must be used so that the Marble can be effectively scaled and translated
        vert2 = new double[] {1, -1, 0, 1};
        radius = (vert1[1] - vert2[1])/2;

        centerX = vert1[0] - radius;
        centerY = vert1[1] - radius;
        centerZ = 0;

        coords = new double[] {centerX, centerY, centerZ, 1}; //Sphere with radius one centered at the origin
    }
    
    /**
     * Scales and transforms the Marble (in world coordinates)
     */
    public void prepare(double[][] transform){
        double[] v1 = Matrix.transpose(Matrix.multiply(transform, Matrix.transpose(vert1)));
        double[] v2 = Matrix.transpose(Matrix.multiply(transform, Matrix.transpose(vert2)));
        radius = (v1[1]/v1[3] - v2[1]/v2[3])/2;
        centerX = v1[0]/v1[3] - radius;
        centerY = v1[1]/v1[3] - radius;
        centerZ = v1[2]/v1[3];
        currentCoords = new double[] {centerX/v1[3], centerY/v1[3], centerZ/v1[3], 1};
    }
    
    /**
     * Gets the current radius of this Marble
     */
    public double getRadius(){
        return radius;
    }
    
    /**
     * Gets the center (x, y, z) coordinates of the Marble
     */
    public double[] getCenter(){
        return currentCoords;
    }
}
