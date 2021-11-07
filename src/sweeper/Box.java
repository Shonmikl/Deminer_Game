package sweeper;

public enum Box {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,

    OPENED,
    CLOSED,
    FLAGGED,
    BOMBED,
    NOBOMB;

    public Object image;

    Box nextNumberBox() {
        return Box.values()[this.ordinal() + 1];
    }

    int getNumber() {
        int nmb = ordinal();
        if(nmb >= Box.NUM1.ordinal() && nmb <= Box.NUM8.ordinal()) {
            return ordinal();
        }
        return -1;
    }
}