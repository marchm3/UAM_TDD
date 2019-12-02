package com.capgemini.tdd.services.game.impl;

import com.capgemini.tdd.dao.BoardDao;
import com.capgemini.tdd.dao.UserDao;
import com.capgemini.tdd.dao.entities.BoardBE;
import com.capgemini.tdd.dao.entities.UserBE;
import com.capgemini.tdd.services.game.NewGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewGameServiceImpl implements NewGameService
{

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private UserDao userDao;

    @Override
    public BoardBE startNewGame(final String playerOneName, final String playerTwoName)
    {
        UserBE player1 = userDao.findByName(playerOneName);
        UserBE player2 = userDao.findByName(playerTwoName);
        Long id = boardDao.findAll().stream().map( x -> x.getId()).max(Long :: compare).orElse(null);
        if(id == null) id = 0L;
        else id = id+1;
        BoardBE newBoard = new BoardBE(player1, player2);
        newBoard.setId(id);
        return boardDao.save(newBoard);
    }
}
