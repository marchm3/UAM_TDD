package com.capgemini.tdd.services.game;

import com.capgemini.tdd.dao.entities.BoardBE;

public interface NewGameService
{
    BoardBE startNewGame(String playerOneName, String player2Neme);

}
