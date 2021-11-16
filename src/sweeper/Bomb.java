package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombCount();
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    public int getTotalBombs() {
        return totalBombs;
    }

    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoordinates();
            if (Box.BOMB == bombMap.get(coord)) {
                continue;
            }
            bombMap.set(coord, Box.BOMB);
            increaseNumbersAroundBomb(coord);
            break;
        }
    }

    private void increaseNumbersAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).nextNumberBox());
            }
        }
    }

    private void fixBombCount() {
        int maxBomb = Ranges.getSquare() / 2;
        if (totalBombs > maxBomb) {
            totalBombs = maxBomb;
        }
    }
}