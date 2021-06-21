package com.board.board.entity;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board extends BaseEntity {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "board_no")
    private Long boardNo;
    
    @Column (name = "board_title", nullable = false)
    private String boardTitle;
    
    @Column (name = "board_content", columnDefinition = "TEXT", nullable = false)
    private String boardContent;
    
    @Column (name = "board_view", nullable = false)
    private Integer boardView;
    
    @Column (name = "board_vote", nullable = false)
    private Integer boardVote;
    
    @Column (name = "board_category", nullable = false)
    @Enumerated (EnumType.STRING)
    private BoardCategory boardCategory;
    
    @Column (name = "board_writer", nullable = false)
    private String boardWriter;
    
    @Builder
    public Board (String boardTitle,
                  String boardContent,
                  String boardWriter,
                  BoardCategory boardCategory) {
        Assert.hasText(boardTitle, "boardTitle must not be empty");
        Assert.hasText(boardContent, "boardContent must not be empty");
        Assert.hasText(boardWriter, "boardWriter must not be empty");
        Assert.hasText(String.valueOf(boardCategory), "boardCategory must not be empty");

        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.boardCategory = boardCategory;
    }

    @PrePersist
    public void prePersist () {
        this.boardView = this.boardView == null ? 0 : this.boardView;
        this.boardVote = this.boardVote == null ? 0 : this.boardVote;
    }
    
}
