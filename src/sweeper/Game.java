package sweeper;

public class Game {
    Matrix bombMap;

    public Game(int columns, int rows) {
        Ranges.setSize(new Coord(columns, rows));
    }

    public void start() {
        bombMap = new Matrix(Box.ZERO);
        bombMap.set(new Coord(0, 0), Box.BOMB);
        bombMap.set(new Coord(0, 1), Box.NUM1);
        bombMap.set(new Coord(1, 0), Box.NUM2);
        bombMap.set(new Coord(1, 1), Box.NUM3);
    }

    public Box getBox(Coord coord) {
        return bombMap.get(coord);
    }
}