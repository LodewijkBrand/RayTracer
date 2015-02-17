/**
 * Class with a few static methods that can perform basic matrix operations
 * @author Lou Brand
 */

public class Matrix{
    public Matrix(){}
    
    /**
     * Multiplies matrix a and b. In that order
     */
    public static double[][] multiply(double[][] a, double[][] b){
        double[][] multiplied = new double [a.length][b[0].length];
        for (int col = 0; col < b[0].length; col++){
            for (int row = 0; row < a.length; row++){
                double entry = 0.0;
                for (int m = 0; m < a[0].length; m++){
                    entry += a[row][m] * b[m][col]; //m is for matching a column in a to row in b
                }
                multiplied[row][col] = entry;
            }
        }
        return multiplied;
    }
    
    /**
     * Gets the transpose of a one row array
     */
    public static double[][] transpose(double[] a){
        double[][] at = new double[a.length][1];
        for (int col = 0; col < a.length; col++){
            at[col][0] = a[col];
        }
        return at;
    }
    
    /**
     * Gets the transpose of a one col array
     */
    public static double[] transpose(double[][] a){
        double[] at = new double[a.length];
        for (int row = 0; row < a.length; row++){
            at[row] = a[row][0];
        }
        return at;
    }
    
    /**
     * Crosses matrix a with matrix b. In that order
     */
    public static double[] cross(double[] a, double[] b){
        double[] n = new double[a.length];
        n[0] = a[1]*b[2] - a[2]*b[1];
        n[1] = -(a[0]*b[2] - a[2]*b[0]);
        n[2] = a[0]*b[1] - a[1]*b[0];
        
        return n;
    }
    
    /**
     * Calculates the dot product of two one row matrices
     */
    public static double dot(double[] a, double[] b){
        double d = 0;
        
        for (int i = 0; i < a.length; i++){
            d += a[i]*b[i];
        }
        
        return d;
    }
    
    /**
     * Normalizes the vector v
     */
    public static double[] normalize(double[] v){
        double[] n = new double[v.length];
        double len = Matrix.dot(v, v);
        len = Math.sqrt(len);
        
        for (int j = 0; j < v.length; j++){
            n[j] = v[j]/len;
        }
        
        return n;
    }
}
