package com.happytrip.domain.board.comment.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCommentRequestDto {

    private Long id;           // 댓글 번호 (PK)
    private int postId;       // 게시글 번호 (FK)
    private String content;    // 내용
    private String writer;     // 작성자

}