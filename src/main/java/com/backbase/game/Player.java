package com.backbase.game;


import java.io.Serializable;

public class Player implements Serializable{
    private String name;
    private int houseIndex;
    private BoardSide side;

    public Player(String name, int houseIndex, BoardSide side) {
        this.name = name;
        this.houseIndex = houseIndex;
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public int getHouseIndex() {
        return houseIndex;
    }

    public int[] getSide() {
        return side.getIndexes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (houseIndex != player.houseIndex) return false;
        if (!name.equals(player.name)) return false;
        return side == player.side;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + houseIndex;
        result = 31 * result + side.hashCode();
        return result;
    }
}
