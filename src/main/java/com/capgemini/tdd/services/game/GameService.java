package com.capgemini.tdd.services.game;

import com.capgemini.tdd.core.data.BoardStatusEnum;
import com.capgemini.tdd.dao.entities.BoardBE;

public interface GameService
{
    BoardBE startNewGame(String playerOneName, String playerTwoName);
    BoardBE getById(Long id);
    BoardBE getByPlayersNames(String playerOne, String playerTwo);
    BoardStatusEnum makeMove(Long boardId, Long x, Long y, String playerName, String value);

}
