package com.backbase.services;

import com.backbase.dao.FileGameDAO;
import com.backbase.game.Game;
import com.backbase.game.State;
import com.backbase.items.Result;
import com.backbase.validators.GameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameEngine {
    private Game game;

    @Autowired
    private FileGameDAO gameDAO;

    @Autowired
    private GameValidator gameValidator;

    public GameEngine() {
    }

    public boolean init() {
        game = new Game();
        return true;
    }

    public void restoreGame() {
        Optional<State> stateOpt = gameDAO.restoreState();
        stateOpt.ifPresent(state -> game = new Game(state));
    }

    public synchronized Result playRound(int startStoreIndex) {
        gameValidator.validate(game, startStoreIndex);

        Result result = game.playRound(startStoreIndex);
        switch (result.status) {
            case ACTIVE:
                storeState();
                break;
            case FINISHED:
                clearStoredState();
        }
        return result;
    }

    private void clearStoredState() {
        gameDAO.deleteState();
    }

    private void storeState() {
        State state = game.getState();
        gameDAO.storeState(state);
    }
}
