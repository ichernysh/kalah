package com.backbase.validators.helpers;


import com.backbase.game.Game;
import com.backbase.validators.PredicateWrapper;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class ValidationHelper {

    public static PredicateWrapper<Integer> validForPlayer(int[] indexes, String message){
        return PredicateWrapper.wrap((startIndex) -> IntStream.of(indexes).anyMatch(x -> x == startIndex), message);
    }

    public static PredicateWrapper<Game> isInitedGame(String message){
        return PredicateWrapper.wrap(Objects::nonNull, message);
    }

}
