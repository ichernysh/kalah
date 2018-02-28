package com.backbase.game;

import com.backbase.items.Result;

import java.util.stream.IntStream;

public class Game {
    private State state;
    private Player firstPlayer;
    private Player secondPlayer;

    public Game() {
       this(new State());
    }

    public Game(State state) {
        this.state = state;
        firstPlayer = new Player("first", 6, BoardSide.TOP);
        secondPlayer = new Player("second", 13, BoardSide.BOTTOM);
        state.setCurrentPlayer(state.getCurrentPlayer() == null ? firstPlayer : state.getCurrentPlayer());
    }

    public Result playRound(int startStoreIndex) {
        int[] data = state.getBoard().getData();
        int steps = data[startStoreIndex];
        data[startStoreIndex] = 0;
        int index = startStoreIndex;
        Player currentPlayer = state.getCurrentPlayer();

        index = handleRegularActions(data, steps, index);
        handleEmptyStore(data, index, currentPlayer);
        handleChangeCurrentPlayer(index, currentPlayer);

        return constructResult(data);
    }

    private Result constructResult(int[] data) {
        long countOfNonEmptyStores = getCountOfNonEmptyStores(data);
        Result result = new Result();
        result.firstHouseSeed = data[firstPlayer.getHouseIndex()];
        result.secondHouseSeed = data[secondPlayer.getHouseIndex()];
        result.currentPlayer = state.getCurrentPlayer();
        if(countOfNonEmptyStores == 0) {
            result.status = GameStatus.FINISHED;
        } else {
            result.status = GameStatus.ACTIVE;
        }
        return result;
    }

    private long getCountOfNonEmptyStores(int[] data) {
        return IntStream.range(0, 13)
                .filter(storeIndex -> storeIndex != firstPlayer.getHouseIndex())
                .filter(storeIndex -> storeIndex != secondPlayer.getHouseIndex())
                .filter(storeIndex -> data[storeIndex] > 0)
                .count();
    }

    private int handleRegularActions(int[] data, int steps, int index) {
        while (steps > 0) {
            index++;
            if (index > 13) {
                index = 0;
            }
            if (index != getOpponent().getHouseIndex()) {
                data[index]++;
                steps--;
            }
        }
        return index;
    }

    private void handleChangeCurrentPlayer(int index, Player currentPlayer) {
        if (index != currentPlayer.getHouseIndex()) {
            state.setCurrentPlayer(currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer);
        }
    }

    private void handleEmptyStore(int[] data, int index, Player currentPlayer) {
        if (IntStream.of(currentPlayer.getSide()).anyMatch(storeIndex -> storeIndex == index) && data[index] == 1) {
            data[currentPlayer.getHouseIndex()] += 1 + data[getOpponent().getSide()[0] + index];
            clearStore(data, index);
        }
    }

    private void clearStore(int[] data, int index) {
        data[getOpponent().getSide()[0] + index] = 0;
        data[index] = 0;
    }

    private Player getOpponent() {
        return state.getCurrentPlayer().equals(firstPlayer) ? secondPlayer : firstPlayer;
    }

    public State getState() {
        return state;
    }

}
