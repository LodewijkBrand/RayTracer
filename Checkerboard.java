import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * Class that represents a Checkerboard
 * @author Lou Brand
 */
 
public class Checkerboard{
    private double[] vert1, vert2, vert3, vert4, normal;
    private ArrayList<Checker> board;
    
    /**
     * Constructor itializes a Checkerboard object around the origin
     * 4-------1
     * |       |
     * |       |
     * |       |
     * 3-------2
     */
    public Checkerboard(){
        vert1 = new double[]{1, 0, -1, 1};
        vert2 = new double[]{1, 0, 1, 1}; //1 in z
        vert3 = new double[]{-1, 0, 1, 1}; //1 in z
        vert4 = new double[]{-1, 0, -1, 1};
        normal = new double[]{0, 1, 0};
        board = new ArrayList<Checker>();
    }
    
    /**
     * Scales and transforms the Checkerboard (in world coordinates)
     */
    public void prepare(double[][] transform){
        double[] v1 = Matrix.transpose(Matrix.multiply(transform, Matrix.transpose(vert1)));
        double[] v2 = Matrix.transpose(Matrix.multiply(transform, Matrix.transpose(vert2)));
        double[] v3 = Matrix.transpose(Matrix.multiply(transform, Matrix.transpose(vert3)));
        double[] v4 = Matrix.transpose(Matrix.multiply(transform, Matrix.transpose(vert4)));
        
        generateCheckerboard(v1, v2, v3, v4, 4);
    }
    
    /**
     * Recursively generates a Checkerboard
     *         mid4
     *      4-------1
     *      |       |
     * mid3 | center| mid1
     *      |       |
     *      3-------2
     *         mid2
     */
    public void generateCheckerboard(double[] v1, double[] v2, double[] v3, double[] v4, int level){
        double[] mid1 = new double[]{(v1[0] + v2[0])/2, (v1[1] + v2[1])/2, (v1[2] + v2[2])/2, (v1[3] + v2[3])/2};
        double[] mid2 = new double[]{(v2[0] + v3[0])/2, (v2[1] + v3[1])/2, (v2[2] + v3[2])/2, (v2[3] + v3[3])/2};
        double[] mid3 = new double[]{(v3[0] + v4[0])/2, (v3[1] + v4[1])/2, (v3[2] + v4[2])/2, (v3[3] + v4[3])/2};
        double[] mid4 = new double[]{(v4[0] + v1[0])/2, (v4[1] + v1[1])/2, (v4[2] + v1[2])/2, (v4[3] + v1[3])/2};
        double[] center = new double[]{(mid1[0] + mid3[0])/2, (mid1[1] + mid3[1])/2, (mid1[2] + mid3[2])/2, (mid1[3] + mid3[3])/2};
        if (level == 0){
            Checker c1 = new Checker(v1, mid1, center, mid4, Color.white);
            Checker c2 = new Checker(mid1, v2, mid2, center, Color.black);
            Checker c3 = new Checker(center, mid2, v3, mid3, Color.white);
            Checker c4 = new Checker(mid4, center, mid3, v4, Color.black);
            board.add(c1);
            board.add(c2);
            board.add(c3);
            board.add(c4);
        } else{
            int nextLevel = level - 1;
            generateCheckerboard(v1, mid1, center, mid4, nextLevel);
            generateCheckerboard(mid1, v2, mid2, center, nextLevel);
            generateCheckerboard(center, mid2, v3, mid3, nextLevel);
            generateCheckerboard(mid4, center, mid3, v4, nextLevel);
        }
    }
    
    /**
     * Gets the normal vector associated with the plane the Checkerboard is on
     */
    public double[] getNormal(){
        return normal;
    }
    
    /**
     * Gets the color of the pixel at point p on the Checkerboard
     */
    public Color getColor(double[] p){
        for (Checker c : board){
            if (c.contains(p)){
                return c.getColor();
            }
        }
        return null;
    }
}
