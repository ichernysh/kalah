package com.backbase.dao;

import com.backbase.game.State;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public abstract class GameDAO {
    public abstract void storeState(State state);

    public abstract Optional<State> restoreState();

    public abstract void deleteState();
}
