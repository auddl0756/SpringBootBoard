package com.board.board.repository;

import com.board.board.entity.Board;
import com.board.board.entity.BoardCategory;
import com.board.board.entity.QBoard;
import com.querydsl.core.BooleanBuilder;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void 저장후_가져오기(){
        String title="title";
        String content="content";
        String writer="writer";
        BoardCategory category= BoardCategory.FREE;

        Board board = Board.builder()
                            .boardTitle(title)
                            .boardContent(content)
                            .boardWriter(writer)
                            .boardCategory(category)
                            .build();

        boardRepository.save(board);
//        System.out.println(boardRepository.findAll().get(0).getBoardView());

        assertThat(boardRepository.findAll().get(0).getBoardTitle()).isEqualTo(title);
        assertThat(boardRepository.findAll().get(0).getBoardCategory()).isEqualTo(category);
    }

    @Test
    public void querydsl테스트(){
        String title="title";
        String content="content";
        String writer="writer";
        BoardCategory category= BoardCategory.FREE;

        Board board = Board.builder()
                .boardTitle(title)
                .boardContent(content)
                .boardWriter(writer)
                .boardCategory(category)
                .build();

        boardRepository.save(board);

        Pageable pageable = PageRequest.of(0,10, Sort.by("boardNo").descending());
        QBoard qBoard = QBoard.board;

        BooleanBuilder builder = new BooleanBuilder();
        String keyword="title";
        BooleanExpression expression = qBoard.boardTitle.contains(keyword);
        builder.and(expression);

        Page<Board> result = boardRepository.findAll(builder,pageable);

        result.stream().forEach(b ->{
            System.out.println(b);
        });
    }
}