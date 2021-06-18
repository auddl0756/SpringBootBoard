package com.board.board.dto;

import com.board.board.entity.BoardCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardDetailDto {
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private BoardCategory boardCategory;
    private Integer boardView;
    private Integer boardVote;
    
    @Builder
    public BoardDetailDto (String boardTitle, String boardContent, String boardWriter, BoardCategory boardCategory, Integer boardView, Integer boardVote) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.boardCategory = boardCategory;
        this.boardView = boardView;
        this.boardVote = boardVote;
    }
}
