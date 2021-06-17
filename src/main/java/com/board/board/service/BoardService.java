package com.board.board.service;

import com.board.board.dto.BoardCreateDto;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.dto.SampleDTO;
import com.board.board.entity.Board;
import com.board.board.entity.SampleEntity;

import java.util.List;

public interface BoardService {
    
    Long save(BoardCreateDto dto);
    
    List<Board> getBoards ();

    PageResponseDTO<BoardCreateDto,Board> getList(PageRequestDTO dto);
    
    default BoardCreateDto entityToDTO (Board board) {
        BoardCreateDto dto
                = BoardCreateDto.builder()
                                .boardNo(board.getBoardNo())
                                .boardTitle(board.getBoardTitle())
                                .boardContent(board.getBoardContent())
                                .boardWriter(board.getBoardWriter())
                                .boardCategory(board.getBoardCategory())
                                .boardView(board.getBoardView())
                                .boardVote(board.getBoardVote())
                                .regDate(board.getRegDate())
                                .modDate(board.getModDate())
                                .build();
        return dto;
    }
    
    default Board dtoToEntity (BoardCreateDto dto) {
        Board entity
                = Board.builder()
                        .boardTitle(dto.getBoardTitle())
                        .boardContent(dto.getBoardContent())
                        .boardWriter(dto.getBoardWriter())
                        .boardCategory(dto.getBoardCategory())
                        .build();

        return entity;
    }
    
}
