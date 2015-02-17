import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Class that controls the ray tracing in a given Scene
 * @author Lou Brand
 */
 
public class RayTracer{
    private Scene myScene;
    private double[] eye; //{0, -400, 1000};
    private double[] light; 
    private ViewWindow myVW;
    
    /**
     * Constructor initializes a RayTracer with a light source and ViewWindow
     */
    public RayTracer(Scene s){
        myScene = s;
        eye = myScene.getEye();
        light = new double[] {400, -600, 1000};
        myVW = new ViewWindow(800, 800, 300);
    }
    
    /**
     * Renders the image using ray tracing
     */
    public BufferedImage render(){
        BufferedImage buffer = myScene.getImage();
        for (int y = myVW.getMinY(); y < myVW.getMaxY(); y++){
            for (int x = myVW.getMinX(); x < myVW.getMaxX(); x++){
                Color c = trace(myVW.getDirection(x, y, eye), 1);
                buffer.setRGB(x - myVW.getMinX(), y - myVW.getMinY(), c.getRGB());
            }
        }
        return buffer;
    }
    
    /**
     * Given a light ray determine a Color based on a light ray that bounces around the Scene
     */
    public Color trace(double[] v, int level){
        double[] pMarble = marbleIntersection(v);
        
        //If the light ray intersects the Marble have it bounce off
        if (pMarble != null && level > 0){
            double[] c = myScene.getMarble().getCenter();
            double[] n = new double[]{pMarble[0] - c[0], pMarble[1] - c[1], pMarble[2] - c[2]};
            double[] r = new double[]{pMarble[0] - eye[0], pMarble[1] - eye[1], pMarble[2] - eye[2]};
            n = Matrix.normalize(n);
            r = Matrix.normalize(r);
            double dot = Matrix.dot(r, n);
            double[] R = new double[]{r[0] - 2*dot*n[0], r[1] - 2*dot*n[1], r[2] - 2*dot*n[2]};
            return trace(R, level-1);
        } 
        
        //If the light ray doesn't intersect the Marble check to see if it intersects the Checkerboard
        else{
            double[] pChecker = checkerboardIntersection(v);
            if (pChecker != null){
                Color c = myScene.getCheckerboard().getColor(pChecker);
                if (c != null){
                    return myScene.getCheckerboard().getColor(pChecker);
                }
            }
            
            //Lazy (not super realistic) specular reflection (determined experimentally...)
            double[] normRay = Matrix.normalize(v);
            double[] normLight = Matrix.normalize(light);
            double cos = Matrix.dot(normRay, normLight);
            if (cos > 0){
                cos = Math.pow(cos, 8);
                cos = 1-cos;
                return new Color(0,(int)(75*cos),(int)(255*cos),(int)(255*cos));
            } else{
                cos = Math.pow(cos, 2);
                return new Color(0,75 -(int)(75*cos),255 - (int)(255*cos),255 - (int)(255*cos));
            }
        }
    }
    
    /**
     * Check to see whether the light ray intersects the Checkerboard
     * Algorithm: Vector intersects the xz plane
     */
    public double[] checkerboardIntersection(double[] v){
        double[] n = myScene.getCheckerboard().getNormal();
        double t = -(Matrix.dot(n, eye))/Matrix.dot(n, v);
        if (t > 0){
            return new double[]{eye[0] + t * v[0], eye[1] + t * v[1], eye[2] + t * v[2]};
        } else{
            return null;
        }
    }
    
    /**
     * Check to see whether the light ray intersects the Marble
     * Algorithm: Vector intersects a sphere (pg. 66) [Janke]
     */
    public double[] marbleIntersection(double[] v){
        double[] normV = Matrix.normalize(v);
        double[] center = myScene.getMarble().getCenter();
        double[] w = new double[]{center[0] - eye[0], center[1] - eye[1], center[2] - eye[2]};
        double L = Matrix.dot(w, normV);
        double a = Math.sqrt(w[0]*w[0] + w[1]*w[1] + w[2]*w[2] - L*L);
        double r = myScene.getMarble().getRadius();
        double d = Math.sqrt(r*r - a*a);
        if (a < r){
            double t = L - d;
            double[] p = new double[]{eye[0] + t*normV[0], eye[1] + t*normV[1], eye[2] + t*normV[2]};
            return p;
        }
        else{
            return null;
        }
    }
}
