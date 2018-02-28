package com.backbase.game;

public enum BoardSide {
    TOP(new int[]{0, 1, 2, 3, 4, 5}),
    BOTTOM(new int[]{7, 8, 9, 10, 11, 12});

    private int[] indexes;

    BoardSide(int[] indexes) {
        this.indexes = indexes;
    }

    public int[] getIndexes() {
        return indexes;
    }
}
