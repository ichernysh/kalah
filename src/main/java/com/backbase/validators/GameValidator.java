package com.backbase.validators;


import com.backbase.game.Game;
import org.springframework.stereotype.Component;

import static com.backbase.validators.helpers.ValidationHelper.isInitedGame;
import static com.backbase.validators.helpers.ValidationHelper.validForPlayer;

@Component
public class GameValidator {

    public GameValidator() {
    }

    public void validate(Game game, int startStoreIndex) {
        isInitedGame("Game does not initialized. Start new game.").test(game).throwIfInvalid();
        validForPlayer(game.getState().getCurrentPlayer().getSide(), "Invalid start position for current user").test(startStoreIndex).throwIfInvalid();
    }
}
