package com.capgemini.tdd.dao.impl;

import com.capgemini.tdd.dao.BoardDao;
import com.capgemini.tdd.dao.entities.BoardBE;
import org.springframework.stereotype.Repository;

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
}
