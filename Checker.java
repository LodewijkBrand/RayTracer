import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * Class that defines a Checker object in our Scene
 * @author Lou Brand
 */

public class Checker{
    private double[] vert1, vert2, vert3, vert4;
    private Path2D path;
    private Color myColor;
    
    /**
     * Initializes a Checker given 4 vertices (world coordinates)
     */
    public Checker(double[] v1, double[] v2, double[] v3, double[] v4, Color c){
        vert1 = v1;
        vert2 = v2;
        vert3 = v3;
        vert4 = v4;
        myColor = c;
    }
    
    /**
     * Checks to see if this point is located in the Checker
     */
    public boolean contains(double[] p){
        if (p[0] >= vert4[0] && p[0] <= vert1[0] && p[2] >= vert1[2] && p[2] <= vert2[2]){
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * Gets the Color associated with this Checker
     */
    public Color getColor(){
        //Turn a checker that contains the point (10, 0, 10) red! (Nice debugging tool)
        int x = 10;
        int z = 1000;
        if (x >= vert4[0] && x <= vert1[0] && z >= vert1[2] && z <= vert2[2]){
            return Color.red;
        }
        return myColor;
    }
}
