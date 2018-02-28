package com.backbase;

import com.backbase.exceptions.APIException;
import com.backbase.services.GameEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameEngineTest {

    @Autowired
    private GameEngine gameEngine;

    @Before
    public void init() {
        gameEngine.init();
    }

    @Test(expected = APIException.class)
    public void when_second_player_tries_to_play_first_users_position() {
        gameEngine.playRound(9);
    }

    @After
    public void clear() {
        gameEngine = null;
    }
}
