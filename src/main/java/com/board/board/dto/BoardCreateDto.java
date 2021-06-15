package com.board.board.dto;

import com.board.board.entity.BoardCategory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BoardCreateDto {
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private BoardCategory boardCategory;
}
