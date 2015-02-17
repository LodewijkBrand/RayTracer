/**
 * Class that defines the viewing window for ray tracing
 * @author Lou Brand
 */

public class ViewWindow{
    private int minX, maxX, minY, maxY, sceneDistance;
    
    /**
     * Constructs a view window given a width, height, and distance from the origin
     */
    public ViewWindow(int width, int height, int d){
        minX = -width/2;
        maxX = width/2;
        minY = -height/2;
        maxY = height/2;
        sceneDistance = d; //ViewWindow's distance from Scene
    }
    
    /**
     * Gets the minimum x of the window
     */
    public int getMinX(){
        return minX;
    }
    
    /**
     * Gets the maximum x of the window
     */
    public int getMaxX(){
        return maxX;
    }
    
    /**
     * Gets the minimum y of the window
     */
    public int getMinY(){
        return minY;
    }
    
    /**
     * Gets the maximum y of the window
     */
    public int getMaxY(){
        return maxY;
    }
    
    /**
     * Gets the direction of the eye vector going into the view window
     */
    public double[] getDirection(double x, double y, double[] eye){
        double[] d = new double[]{x - (int) eye[0], y - (int) eye[1], sceneDistance - (int)eye[2]};
        return d;
    }
}
