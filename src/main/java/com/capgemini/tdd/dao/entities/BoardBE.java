package com.capgemini.tdd.dao.entities;

public class BoardBE
{
    private Long id;
    private UserBE playerOne;
    private UserBE playerTwo;

    public BoardBE(final UserBE pPlayerOne, final UserBE pPlayerTwo)
    {
        playerOne = pPlayerOne;
        playerTwo = pPlayerTwo;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long pId)
    {
        id = pId;
    }

    public UserBE getPlayerOne()
    {
        return playerOne;
    }

    public UserBE getPlayerTwo()
    {
        return playerTwo;
    }

    @Override
    public String toString()
    {
        return "BoardBE{" +
               "id=" + id +
               ", playerOne=" + playerOne +
               ", playerTwo=" + playerTwo +
               '}';
    }
}
