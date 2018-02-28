package com.backbase.items;

import java.io.Serializable;

public class Board implements Serializable {

    private int[] data;

    public Board() {
        data = new int[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
    }

    public Board(int[] data) {
        this.data = data;
    }

    public int[] getData() {
        return data;
    }
}
