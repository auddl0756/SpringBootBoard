package com.board.board.service;

import com.board.board.dto.BoardCreateDto;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.entity.Board;
import com.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long save (BoardCreateDto dto){
//        Board board = Board.builder()
//                            .boardNo(requestDto.getBoardNo())
//                           .boardTitle(requestDto.getBoardTitle())
//                           .boardContent(requestDto.getBoardContent())
//                           .boardWriter(requestDto.getBoardWriter())
//                           .boardCategory(requestDto.getBoardCategory())
//                            .boardView(requestDto.getBoardView())
//                            .boardVote(requestDto.getBoardVote())
//                           .build();
        Board board = dtoToEntity(dto);

        Board savedBoard = boardRepository.save(board);
        return savedBoard.getBoardNo();
    }
    
    /**
     * 게시판 전체 글 전송(추후 paging 관련 논의 필요) <br>
     * @Return boards 전체 게시판글
     */
    @Override
    @Transactional
    public List<Board> getBoards (){
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    @Override
    public PageResponseDTO<BoardCreateDto, Board> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("boardNo").descending());

        Page<Board> response = boardRepository.findAll(pageable);

        Function<Board,BoardCreateDto> function = (this::entityToDTO);

        return new PageResponseDTO<>(response,function);
    }
}
