package setcore;

import java.util.HashSet;

public class PointSet extends HashSet<Point> {
    public HashSet<PointSet> findSets() {
        HashSet<PointSet> sets = new HashSet<PointSet>();
        
        for(Point x : this) {
            for(Direction direction : new Directions()) {
                PointSet set = new PointSet();
                set.add(x);

                for(Point next = x.move(direction); contains(next) && set.size() < Point.variability(); next = next.move(direction)) {
                    set.add(next);
                }
                
                if(set.size() == Point.variability())
                    sets.add(set);
            }
        }
        
        return sets;
    }
}
