package com.happytrip.domain.board.model.dto;

import lombok.Data;

@Data
public class BoardDto {

    private int BoardNo, commentCnt, readCnt;
    private String title, content, userNickname;
    private String datetime;
}
