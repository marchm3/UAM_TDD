package com.capgemini.tdd.services.game;

import com.capgemini.tdd.dao.entities.MoveBE;

import java.util.List;

public interface MoveService
{

    void makeMove(Long boardId, Long x, Long y, String playerName, String value);

    List<MoveBE> findByBoardId(Long boardId);

}
