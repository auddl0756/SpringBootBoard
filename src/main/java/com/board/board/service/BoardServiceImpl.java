package com.board.board.service;

import com.board.board.dto.BoardCreateDto;
import com.board.board.entity.Board;
import com.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl {
    
    private final BoardRepository boardRepository;
    
    @Transactional
    public Long save (BoardCreateDto requestDto){
        Board board = Board.builder()
                           .boardTitle(requestDto.getBoardTitle())
                           .boardContent(requestDto.getBoardContent())
                           .boardWriter(requestDto.getBoardWriter())
                           .boardCategory(requestDto.getBoardCategory())
                           .build();
        Board savedBoard = boardRepository.save(board);
        return savedBoard.getBoardNo();
    }
    
    
}
