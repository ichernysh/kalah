package com.backbase;

import com.backbase.game.BoardSide;
import com.backbase.game.Game;
import com.backbase.game.GameStatus;
import com.backbase.game.Player;
import com.backbase.game.State;
import com.backbase.items.Board;
import com.backbase.items.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class GameTest {

    private Game game;

    @Before
    public void init() {
        game = new Game();
    }

    @Test
    public void after_init_state_should_have_12_houses_with_6_seeds_in_each_and_2_stores() {
        State state = game.getState();
        int[] board = state.getBoard().getData();

        assertThat(board, is(new int[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0}));
    }

    @Test
    public void when_play_round_starting_from_position_two_number_of_seeds_in_next_six_stores_should_increased_by_one() {
        State state = game.getState();
        game.playRound(2);
        int[] board = state.getBoard().getData();

        assertThat(board, is(new int[]{6, 6, 0, 7, 7, 7, 1, 7, 7, 6, 6, 6, 6, 0}));
    }

    @Test
    public void when_second_user_plays_round_starting_from_10_number_of_seeds_in_next_six_stores_should_increased_by_one() {
        State state = game.getState();
        state.setCurrentPlayer(new Player("second", 13, BoardSide.BOTTOM));
        game.playRound(9);
        int[] board = state.getBoard().getData();

        assertThat(board, is(new int[]{7, 7, 6, 6, 6, 6, 0, 6, 6, 0, 7, 7, 7, 1}));
    }

    @Test
    public void when_next_store_is_opponents_house_skip_it() {
        Board board = new Board(new int[]{6, 6, 6, 4, 3, 11, 0, 6, 6, 6, 6, 6, 6, 0});

        State state = game.getState();
        state.setBoard(board);

        game.playRound(5);
        int[] data = state.getBoard().getData();


        assertThat(data, is(new int[]{7, 7, 7, 5, 3, 0, 1, 7, 7, 7, 7, 7, 7, 0}));
    }

    @Test
    public void game_state_should_not_be_null() {
        State state = game.getState();
        assertThat(state, is(notNullValue()));
    }

    @Test
    public void when_init_for_first_user_current_user_one() {
        State state = game.getState();
        Player player = state.getCurrentPlayer();
        assertThat(player.getName(), is("first"));
    }

    @Test
    public void when_round_finished_current_user_should_change() {
        State state = game.getState();

        int startStoreIndex = 2;
        game.playRound(startStoreIndex);

        Player player = state.getCurrentPlayer();
        assertThat(player.getName(), is("second"));
    }

    @Test
    public void when_finish_at_empty_store_of_current_player_take_opposite_and_current_seed_to_house() {
        Board board = new Board(new int[]{2, 6, 0, 4, 3, 9, 0, 6, 6, 10, 6, 7, 7, 0});

        State state = game.getState();
        state.setBoard(board);

        game.playRound(0);
        int[] data = state.getBoard().getData();

        assertThat(data, is(new int[]{0, 7, 0, 4, 3, 9, 11, 6, 6, 0, 6, 7, 7, 0}));
    }

    @Test
    public void when_finish_at_current_user_house_user_should_not_change() {
        Board board = new Board(new int[]{2, 6, 0, 3, 3, 9, 1, 6, 6, 10, 6, 7, 7, 0});

        State state = game.getState();
        state.setBoard(board);

        game.playRound(3);
        int[] data = state.getBoard().getData();

        assertThat(data, is(new int[]{2, 6, 0, 0, 4, 10, 2, 6, 6, 10, 6, 7, 7, 0}));
        assertThat(state.getCurrentPlayer().getName(), is("first"));
    }

    @Test
    public void when_all_stores_are_empty_result_should_be_finished() {
        Board board = new Board(new int[]{0, 0, 0, 0, 0, 0, 36, 0, 0, 0, 0, 0, 0, 36});

        State state = game.getState();
        state.setBoard(board);

        Result result = game.playRound(3);
        assertThat(result.status, is(GameStatus.FINISHED));
    }


    @After
    public void clear() {
        game = null;
    }

}
