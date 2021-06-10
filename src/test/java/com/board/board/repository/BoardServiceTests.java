package com.board.board.repository;

import com.board.board.dto.BoardCreateDto;
import com.board.board.entity.Board;
import com.board.board.entity.BoardCategory;
import com.board.board.entity.Member;
import com.board.board.service.BoardServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith (SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTests {
    @Autowired
    EntityManager em;
    @Autowired
    BoardServiceImpl boardServiceImpl;
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void 게시판생성() throws Exception {
        // given
        Board board = createBoard("testWriter", "testContent", "testTitle", BoardCategory.FREE);
    
//        BoardCreateDto boardCreateDto = new BoardCreateDto()
        
        // when

        // then
    }
    
    private Board createBoard (String boardWriter,
                               String boardContent,
                               String boardTitle,
                               BoardCategory boardCategory) {
        Board board = Board.builder()
             .boardWriter(boardWriter)
             .boardContent(boardContent)
             .boardTitle(boardTitle)
             .boardCategory(boardCategory)
             .build();
        em.persist(board);
        
        return board;
    }
}
