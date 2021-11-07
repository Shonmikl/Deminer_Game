package sweeper;

public class Game {
    private final Bomb bomb;
    private final Flag flag;
    private GameState state;

    public Game(int columns, int rows, int bombs) {
        Ranges.setSize(new Coord(columns, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public Box getBox(Coord coord) {
        if (Box.OPENED == flag.get(coord)) {
            return bomb.get(coord);
        }
        return flag.get(coord);
    }

    public void pressLeftButton(Coord coord) {
        if (isGameOver()) return;
        openBox(coord);
        checkWinner();
    }

    private void checkWinner() {
        if (GameState.PLAYED == state) {
            if (flag.getTotalClosed() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
                flag.setFlagsOnLastClosedBoxes();
            }
        }
    }

    private void openBox(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED:
            case FLAGGED:
                break;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO -> openBoxesAroundZero(coord);
                    case BOMB -> openBombs(coord);
                    default -> flag.setOpenedToBox(coord);
                }
        }
    }

    private void openBombs(Coord bombedCoord) {
        flag.setBombedToBox(bombedCoord);
        for (Coord coord : Ranges.getAllCoords()) {
            if (bomb.get(coord) == Box.BOMB) {
                flag.setOpenedToClosedBox(coord);
            } else {
                flag.setNobombToFlaggedBomb(coord);
            }
        }
        state = GameState.BOMBED;
    }

    public int getTotalBombs() {
        return bomb.getTotalBombs();
    }

    public int getTotalFlagged() {
        return flag.getTotalFlagged();
    }

    private void openBoxesAroundZero(Coord coord) {
        System.out.print("[" + coord.x + ":" + coord.y + "]");
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openBox(around);
        }
    }

    public void pressRightButton(Coord coord) {
        if (isGameOver()) return;
        flag.toggleFlaggedToBox(coord);
    }

    private boolean isGameOver() {
        if (GameState.PLAYED != state) {
            start();
            return true;
        }
        return false;
    }

    public GameState getState() {
        return state;
    }
}