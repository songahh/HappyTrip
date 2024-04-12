package com.happytrip.domain.board.service;

import com.happytrip.domain.board.model.dto.BoardDto;
import com.happytrip.domain.board.model.dto.BoardListDto;

import java.util.Map;

public interface BoardService {


    void writeBoard(BoardDto boardDto) throws Exception;

    void modifyBoard(BoardDto boardDto)  throws Exception;

    BoardListDto listBoard(Map<String, String> map) throws Exception;

    BoardDto getBoard(int boardNo) throws Exception;

    void updateHit(Map<String, Object> param) throws Exception;

    void deleteBoard(int boardNo) throws Exception;

}
