package com.capgemini.tdd.dao.impl;

import com.capgemini.tdd.dao.BoardDao;
import com.capgemini.tdd.dao.entities.BoardBE;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao
{

    @Override
    public List<BoardBE> findAll()
    {
        return null;
    }

    @Override
    public BoardBE save(final BoardBE boardBE)
    {
        return null;
    }

    @Override
    public BoardBE findById(final Long id)
    {
        return null;
    }

    @Override
    public BoardBE getByPlayersNames(final String playerOne, final String playerTwo)
    {
        return findAll().stream().filter(x -> x.getPlayerOne().equals(playerOne) && x.getPlayerTwo().equals(playerTwo)).max(
                Comparator.comparing(BoardBE::getId)).orElse(null);
    }
}
