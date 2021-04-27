package setcore;

import java.util.AbstractCollection;
import java.util.Iterator;

public class Directions extends AbstractCollection<Direction> {
    public int size() { 
        return (int)Math.pow(3, Direction.dimension()) - 1; 
    }
    
    public Iterator<Direction> iterator() {
        return new Iterator<Direction>() {
            private int positiveDirectionsCount = (int)Math.pow(2, Direction.dimension());
            private int positiveDirectionIndex = 1;
            private int directionsCount = 2;
            private int directionIndex = 0;
            private int onBitsCount = 1;
            private int[] onBitsPositions = new int[Direction.dimension()];
            
            public boolean hasNext() { 
                return positiveDirectionIndex < positiveDirectionsCount && directionIndex < directionsCount; 
            }
            
            public Direction next() {
                Direction direction = new Direction();
                
                for(int i = 0, tie = directionIndex; i < onBitsCount; i++, tie >>= 1) {
                    if(tie % 2 != 0)
                        direction.set(onBitsPositions[i], -1);
                    else
                        direction.set(onBitsPositions[i], 1);
                }
                
                if(directionIndex < directionsCount - 1) 
                    directionIndex++;
                else {
                    positiveDirectionIndex++;
                    directionIndex = 0;
                    onBitsCount = 0;
                    for(int i = 0, tie = positiveDirectionIndex; i < Direction.dimension(); i++, tie >>= 1) {
                        if(tie % 2 != 0) {
                            onBitsPositions[onBitsCount] = i;
                            onBitsCount++;
                        }
                    }
                    directionsCount = (int)Math.pow(2, onBitsCount);
                }

                return direction;
            }
            
            public void remove() { 
                throw new UnsupportedOperationException("Direction can not be removed by the iterator"); 
            }
        };
    }
}
