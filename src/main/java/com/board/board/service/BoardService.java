package com.board.board.service;

import com.board.board.dto.BoardCreateDto;
import com.board.board.dto.BoardDetailDto;
import com.board.board.entity.Board;
import com.board.board.entity.SampleEntity;
import com.board.board.web.SampleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {
    
    Long save (BoardCreateDto requestDto);
    
    List<Board> getBoards ();
    
    BoardDetailDto getBoard(Long boardNo);
}
