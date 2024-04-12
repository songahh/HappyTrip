package com.happytrip.domain.board.model;

import com.happytrip.domain.board.model.dto.BoardDto;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
public interface BoardMapper {

    void writeBoard(BoardDto boardDto) throws SQLException;

    void modifyBoard(BoardDto boardDto) throws SQLException;

    List<BoardDto> listBoard(Map<String, Object> param) throws SQLException;

    int getTotalBoardCount(Map<String, Object> param) throws SQLException;

    BoardDto getBoard(int boardNo) throws SQLException;

    void updateHit(Map<String, Object> param) throws SQLException;

    void deleteBoard(int boardNo) throws SQLException;

}


