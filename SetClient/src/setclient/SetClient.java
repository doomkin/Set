package setclient;

import setcore.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SetClient {
    public static void main(String[] args) {
        // file input.txt contains dimension, variability and open cards
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt")).useDelimiter("[\\n|\\s]+");
        } catch (FileNotFoundException e) {
            System.out.println("File input.txt not found in current directory");
        }
        
        Point.setVariability(scanner.nextInt());
        Direction.setDimension(scanner.nextInt());
        
        PointSet openCards = new PointSet();
        while(scanner.hasNext()) {
            Point p = new Point(scanner.next());
            openCards.add(p);
        }
        
        // find all sets at open cards
        System.out.printf("Hello, Set %d^%d.\n", Point.variability(), Point.dimension());
        System.out.println("Open cards: " + openCards);
        
        int i = 1;
        for(PointSet set : openCards.findSets()) {
            System.out.println("Set " + i++ + ": " + set);
        }
    }
}
