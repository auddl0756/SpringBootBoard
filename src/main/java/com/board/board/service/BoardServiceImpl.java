package com.board.board.service;

import com.board.board.dto.BoardCreateDto;
import com.board.board.dto.BoardDetailDto;
import com.board.board.entity.Board;
import com.board.board.repository.BoardRepository;
import com.board.board.web.SampleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    
    private final BoardRepository boardRepository;
    
    @Override
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
    
    /**
     * 게시판 전체 글 전송(추후 paging 관련 논의 필요) <br>
     * @Return boards 전체 게시판글
     */
    @Transactional
    public List<Board> getBoards (){
        List<Board> boards = boardRepository.findAll();
        return boards;
    }
    
    /**
     * 게시판 글 하나
     * @return board 가져온 게시판 글 하나
     */
    @Transactional
    public BoardDetailDto getBoard (Long boardNo) {
        Board findBoard = boardRepository.findOneByBoardNo(boardNo).orElseThrow(NoSuchElementException::new);
        BoardDetailDto board = BoardDetailDto.builder()
                                             .boardTitle(findBoard.getBoardTitle())
                                             .boardContent(findBoard.getBoardContent())
                                             .boardWriter(findBoard.getBoardWriter())
                                             .boardCategory(findBoard.getBoardCategory())
                                             .boardView(findBoard.getBoardView())
                                             .boardVote(findBoard.getBoardVote())
                                             .build();
        return board;
    }
    
}
