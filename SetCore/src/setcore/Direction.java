package setcore;

import java.util.ArrayList;
import java.util.Collection;

public class Direction extends ArrayList<Integer> {
    private static int DIMENSION = -1;
    
    public static void setDimension(int dimension) {
        if(DIMENSION > 0)
            throw new IllegalStateException("Dimension can not be changed");
        else
            if(dimension <= 0)
                throw new IllegalStateException("Dimension must be positive");
            else
                DIMENSION = dimension;
    }
    
    public static int dimension() {
        if(DIMENSION == -1)
            throw new IllegalStateException("Dimension is not initialized");
        return DIMENSION;
    }
    
    public Direction() {
        super(dimension());
        for(int i = 0; i < dimension(); i++) { 
            add(0); 
        }
    }
 
    public Direction(Collection<? extends Integer> coordinates) {
        super(coordinates);
        if(coordinates.size() != dimension())
            throw new IndexOutOfBoundsException("Collection size must be equal to " + dimension());
    }
}
