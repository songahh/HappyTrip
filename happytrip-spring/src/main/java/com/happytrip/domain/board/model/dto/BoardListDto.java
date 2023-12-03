package com.happytrip.domain.board.model.dto;


import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BoardListDto {

    private List<BoardDto> Boards;
    private int currentPage;
    private int totalPageCount;
}
