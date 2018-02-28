package com.backbase.controllers;

import com.backbase.items.Result;
import com.backbase.services.GameEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameEngine gameEngine;

    @RequestMapping("/init")
    public String init() {
        gameEngine.init();
        return "OK";
    }

    @RequestMapping("/play")
    public Result playRound(@RequestParam(value="position") int position) {
        return gameEngine.playRound(position);
    }

    @RequestMapping("/restore")
    public String restore() {
        gameEngine.restoreGame();
        return "Game restored";
    }


}
