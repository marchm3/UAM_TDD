package com.capgemini.tdd.dao.entities;

import com.capgemini.tdd.dao.enums.MatchResultEnum;

public class MatchResultBE
{

    private Long id;
    private BoardBE board;
    private UserBE playerOne;
    private UserBE playerTwo;
    private MatchResultEnum result;

    public MatchResultBE(final BoardBE pBoard, final UserBE pPlayerOne, final UserBE pPlayerTwo, final MatchResultEnum pResult)
    {
        board = pBoard;
        playerOne = pPlayerOne;
        playerTwo = pPlayerTwo;
        result = pResult;
    }

    public Long getId()
    {
        return id;
    }

    public BoardBE getBoard()
    {
        return board;
    }

    public UserBE getPlayerOne()
    {
        return playerOne;
    }

    public UserBE getPlayerTwo()
    {
        return playerTwo;
    }

    public MatchResultEnum getResult()
    {
        return result;
    }

    @Override
    public String toString()
    {
        return "MatchResultBE{" +
               "id=" + id +
               ", board=" + board +
               ", playerOne=" + playerOne +
               ", playerTwo=" + playerTwo +
               ", result=" + result +
               '}';
    }
}
