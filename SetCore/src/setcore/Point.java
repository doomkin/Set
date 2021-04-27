package setcore;

import java.util.Collection;

public class Point extends Direction {
    private static int VARIABILITY = -1;
    
    public static void setVariability(int variability) {
        if(VARIABILITY > 0)
            throw new IllegalStateException("Variability can not be changed");
        else
            if(variability <= 0)
                throw new IllegalStateException("Variability must be positive");
            else
                VARIABILITY = variability;
    }
    
    public static int variability() {
        if(VARIABILITY == -1)
            throw new IllegalStateException("Variability is not initialized");
        return VARIABILITY;
    }
    
    public Point() { 
        super();
    }

    public Point(Collection<? extends Integer> coordinates) {
        super(coordinates);
        if(outOfBounds())
            throw new IndexOutOfBoundsException("Coorditanes must be between 0 and " + variability());
    }

    public Point(String coordinatesString) {
        super();
        if(coordinatesString.length() != dimension())
            throw new IndexOutOfBoundsException("String lenght must be equal to " + dimension());
        for(int i = 0; i < dimension(); i++) {
            set(i, Integer.parseInt(coordinatesString.substring(i, i+1), 16));
        }
        if(outOfBounds())
            throw new IndexOutOfBoundsException("Coorditanes must be between 0 and " + variability());
    }
    
    public String toString() {
        String result = "";
        for(Integer i : this) {
            result += i > 9 ? (char)(i + 65) : (char)(i + 48);
        }
        return result;
    }
    
    public Point move(Direction direction) {
        Point result = new Point();
        for(int i = 0; i < size(); i++) {
            if(get(i) + direction.get(i) < 0)
                result.set(i, variability() - 1);
            else
                if(get(i) + direction.get(i) >= variability())
                    result.set(i, 0);
                else
                    result.set(i, get(i) + direction.get(i));
        }
        return result;
    }
    
    public boolean outOfBounds() {
        boolean result = false;
        for(Integer i : this) {
            if(i < 0 || i >= variability()) {
                result = true;
                break;
            }
        }
        return result;
    }
}
