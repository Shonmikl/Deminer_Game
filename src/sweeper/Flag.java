package sweeper;

class Flag {
    private Matrix flagMap;
    private int totalFlagged;
    private int totalClosed;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        totalFlagged = 0;
        totalClosed = Ranges.getSquare();
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    int getTotalFlagged() {
        return totalFlagged;
    }

    int getTotalClosed() {
        return totalClosed;
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        totalClosed--;
    }

    private void setFlaggedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGGED);
        totalFlagged++;
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
        totalFlagged--;
    }

    void toggleFlaggedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGGED -> setClosedToBox(coord);
            case CLOSED -> setFlaggedToBox(coord);
        }
    }

    void setFlagsOnLastClosedBoxes() {
        for (Coord coord : Ranges.getAllCoords()) {
            if (Box.CLOSED == flagMap.get(coord)) {
                setFlaggedToBox(coord);
            }
        }
    }

    public void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToClosedBox(Coord coord) {
        if (Box.CLOSED == flagMap.get(coord)) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    void setNoBombToFlaggedBomb(Coord coord) {
        if (Box.FLAGGED == flagMap.get(coord)) {
            flagMap.set(coord, Box.NOBOMB);
        }
    }

    int getCountOfFlaggedBoxesAround() {
        int count = 0;
        for (Coord coord : Ranges.getAllCoords()) {
            if (flagMap.get(coord) == Box.FLAGGED) {
                count++;
            }
        }
        return count;
    }
}