package com.capgemini.tdd.rest;

import com.capgemini.tdd.dao.entities.BoardBE;
import com.capgemini.tdd.mappers.BoardMapper;
import com.capgemini.tdd.services.TO.BoardTO;
import com.capgemini.tdd.services.game.GameService;
import com.capgemini.tdd.services.requestparams.NewGameRequestParams;
import com.capgemini.tdd.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/game")
public class GameRestService
{

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/new")
    @ResponseBody
    public BoardTO startNewGame(@RequestBody NewGameRequestParams requestParams) {
        return BoardMapper.fromBE(gameService.startNewGame(requestParams.getPlayerOneName(), requestParams.getPlayerTwoName()));
    }
}
