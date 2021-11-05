package sweeper;

import java.util.ArrayList;

public class Ranges {
    public static Coord size;
    private static ArrayList<Coord>  allCoords;

    public static Coord getSize() {
        return size;
    }

    public static void setSize(Coord size) {
        Ranges.size = size;
        // not this? //
        allCoords = new ArrayList<Coord>();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                allCoords.add(new Coord(x, y));
            }
        }
    }

    public static void setSize ( int columns, int rows ) {
        Coord size = new Coord(columns, rows);
        setSize(size);
    }

    public static ArrayList<Coord> getAllCoords () {
        return allCoords;
    }

}
