package com.board.board.service;

import com.board.board.dto.BoardCreateDto;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.entity.Board;
import com.board.board.entity.BoardCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Transactional
    @Test
    public void 페이징테스트(){
        LongStream.rangeClosed(1,25).forEach(l->{
            BoardCreateDto dto
                    = BoardCreateDto.builder()
                                    .boardTitle("title"+l)
                                    .boardContent("content"+l)
                                    .boardWriter("writer"+l)
                                    .boardCategory(BoardCategory.FREE)
                                    .build();

            boardService.save(dto);
        });

        int pageSize=10;

        PageRequestDTO pageRequestDTO
                = PageRequestDTO.builder()
                                .page(1)
                                .size(pageSize)
                                .build();

        PageResponseDTO<BoardCreateDto, Board> responseDTO = boardService.getList(pageRequestDTO);

        System.out.println(responseDTO.getNowPage());
        System.out.println(responseDTO.isPrevExist());
        System.out.println(responseDTO.getTotalPage());

        for(BoardCreateDto dto:responseDTO.getDtoList()){
            System.out.println(dto);
        }

        assertThat(responseDTO.getDtoList().size()).isEqualTo(pageSize);
    }
}