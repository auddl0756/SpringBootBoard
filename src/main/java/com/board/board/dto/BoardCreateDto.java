package com.board.board.dto;

import com.board.board.entity.BoardCategory;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BoardCreateDto {
    private Long boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private BoardCategory boardCategory;
    private Integer boardView,boardVote;
    private LocalDateTime regDate,modDate;
}
