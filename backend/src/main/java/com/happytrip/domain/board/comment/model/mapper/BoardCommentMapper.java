package com.happytrip.domain.board.comment.model.mapper;

import com.happytrip.domain.board.comment.model.BoardCommentRequestDto;
import com.happytrip.domain.board.comment.model.BoardCommentResponseDto;

import java.util.List;


public interface BoardCommentMapper {

    /**
     * 댓글 저장
     * @param params - 댓글 정보
     */
    void save(BoardCommentRequestDto params);

    /**
     * 댓글 상세정보 조회
     * @param id - PK
     * @return 댓글 상세정보
     */
    BoardCommentResponseDto findById(Long id);

    /**
     * 댓글 수정
     * @param params - 댓글 정보
     */
    void update(BoardCommentRequestDto params);

    /**
     * 댓글 삭제
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 댓글 리스트 조회
     * @param postId - 게시글 번호 (FK)
     * @return 댓글 리스트
     */
    List<BoardCommentResponseDto> findAll(Long postId);

    /**
     * 댓글 수 카운팅
     * @param postId - 게시글 번호 (FK)
     * @return 댓글 수
     */
    int count(Long postId);

}