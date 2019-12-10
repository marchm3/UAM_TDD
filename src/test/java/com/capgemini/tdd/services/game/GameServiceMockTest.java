package com.capgemini.tdd.services.game;

import com.capgemini.tdd.core.data.Board;
import com.capgemini.tdd.core.data.BoardStatusEnum;
import com.capgemini.tdd.core.data.Point;
import com.capgemini.tdd.core.service.BoardBuilder;
import com.capgemini.tdd.core.service.impl.BoardBuilderImpl;
import com.capgemini.tdd.dao.entities.BoardBE;
import com.capgemini.tdd.dao.entities.MatchResultBE;
import com.capgemini.tdd.dao.entities.MoveBE;
import com.capgemini.tdd.dao.entities.UserBE;
import com.capgemini.tdd.dao.enums.MoveValueEnum;
import com.capgemini.tdd.services.game.impl.GameServiceImpl;
import com.capgemini.tdd.services.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceMockTest
{

    @Mock
    private MoveService moveService;

    @Spy
    private BoardBuilderImpl boardBuilder;

    @Mock
    private MatchResultService matchResultService;

    @Mock
    private UserService userService;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private GameServiceImpl gameService;

    @Captor
    private ArgumentCaptor<Long> captorForLong;

    @Captor
    private ArgumentCaptor<String> captorForString;

    @Test
    public void shouldMakeDraw()
    {
        //given
        MatchResultBE matchResultMock = Mockito.mock(MatchResultBE.class);
        Mockito.when(matchResultService.findByBoardId(Mockito.anyLong())).thenReturn(null).thenReturn(matchResultMock);

        MoveBE moveMock = Mockito.mock(MoveBE.class);

        Mockito.doReturn(moveMock)
               .when(moveService)
               .makeMove(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyString());

        Board board = prepareBoardForDraw();
        Mockito.doReturn(board).when(boardBuilder).buildBoard(Mockito.anyLong());

        UserBE userMock = Mockito.mock(UserBE.class);
        Mockito.doReturn("").when(userMock).getName();
        Mockito.when(userService.findByName(Mockito.anyString())).thenReturn(userMock);
        Mockito.doReturn(matchResultMock).when(matchResultService).save(Mockito.any(MatchResultBE.class));

        BoardBE boardMock = Mockito.mock(BoardBE.class);
        Mockito.when(boardService.findById(Mockito.anyLong())).thenReturn(boardMock);
        Mockito.doReturn(userMock).when(boardMock).getPlayerOne();
        Mockito.doReturn(userMock).when(boardMock).getPlayerTwo();

        //when
        BoardStatusEnum boardStatus = gameService.makeMove(1000L, 2000L, 2000L, "FakeName", "O");

        //then
        Mockito.verify(moveService)
               .makeMove(captorForLong.capture(), captorForLong.capture(), captorForLong.capture(), captorForString.capture(),
                         captorForString.capture());
        Assert.assertEquals(Arrays.asList(1000L, 2000L, 2000L), captorForLong.getAllValues());
        Assert.assertEquals(Arrays.asList("FakeName", "O"), captorForString.getAllValues());
        Assert.assertEquals(BoardStatusEnum.DRAW, boardStatus);
        Mockito.verify(userService, Mockito.times(2)).findByName(Mockito.anyString());
    }

    private Board prepareBoardForDraw()
    {
        Board board = new Board();
        board.put(new Point(0L, 0L), MoveValueEnum.X);
        board.put(new Point(0L, 1L), MoveValueEnum.O);
        board.put(new Point(0L, 2L), MoveValueEnum.X);

        board.put(new Point(1L, 0L), MoveValueEnum.O);
        board.put(new Point(1L, 1L), MoveValueEnum.X);
        board.put(new Point(1L, 2L), MoveValueEnum.O);

        board.put(new Point(2L, 0L), MoveValueEnum.O);
        board.put(new Point(2L, 1L), MoveValueEnum.X);
        board.put(new Point(2L, 2L), MoveValueEnum.O);

        return board;
    }

    @Test
    public void playerCantMakeDoubleMove() {
        //given
        MoveBE moveMock = Mockito.mock(MoveBE.class);

        Mockito.doReturn(moveMock)
                .when(moveService)
                .makeMove(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyString());

        UserBE userMock = Mockito.mock(UserBE.class);
        Mockito.doReturn("FakeName").when(userMock).getName();
        Mockito.when(userService.findByName("FakeName")).thenReturn(userMock);

        UserBE userMock2 = Mockito.mock(UserBE.class);
        Mockito.doReturn("FakeName 2").when(userMock2).getName();
        Mockito.when(userService.findByName("FakeName 2")).thenReturn(userMock2);

        Board board = new Board();
        Mockito.doReturn(board).when(boardBuilder).buildBoard(Mockito.anyLong());

        BoardBE boardMock = Mockito.mock(BoardBE.class);
        Mockito.when(boardService.findById(Mockito.anyLong())).thenReturn(boardMock);
        Mockito.doReturn(userMock).when(boardMock).getPlayerOne();
        Mockito.doReturn(userMock2).when(boardMock).getPlayerTwo();

        //when
        gameService.makeMove(1000L, 2000L, 2000L, "FakeName", "O");
        gameService.makeMove(1000L, 3000L, 3000L, "FakeName", "O");

        //then
        Mockito.verify(moveService, Mockito.times(1)).makeMove(
                Mockito.anyLong(),
                Mockito.anyLong(),
                Mockito.anyLong(),
                Mockito.anyString(),
                Mockito.anyString()
        );
    }

}
