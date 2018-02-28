package com.backbase.game;

import com.backbase.items.Board;

import java.io.Serializable;

public class State implements Serializable{
    private Player currentPlayer;
    private Board board;

    public State() {
        board = new Board();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
