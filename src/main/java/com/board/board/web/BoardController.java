package com.board.board.web;

import com.board.board.dto.BoardCreateDto;
import com.board.board.dto.PageRequestDTO;
import com.board.board.entity.Board;
import com.board.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardServiceImpl boardServiceImpl;
    
    @GetMapping ("/board")
    public String board(){
        log.info("/board 경로로 get 요청이 들어왔습니다.");
        return "board/board";
    }
    
    @GetMapping("/board/new")
    public String createBoard (Model model){
        log.info("/board/new 경로로 get 요청이 들어왔습니다.");
        model.addAttribute("form", new BoardCreateDto());
        return "board/boardForm";
    }
    
    @PostMapping("/board/new")
    public String createBoard (BoardCreateDto requestDto) {
        log.info("/board/new 경로로 post 요청이 들어왔습니다.");
        boardServiceImpl.save(requestDto);
        return "redirect:/boards";
    }
    
//    @GetMapping("/boards")
//    public String getBoards (Model model){
//        log.info("/boards 경로로 get 요청이 들어왔습니다.");
//        List<Board> boards = boardServiceImpl.getBoards();
//        model.addAttribute("boards", boards);
//        return "board/boardList";
//    }

    @GetMapping("/boards")
    public String getBoardList(PageRequestDTO pageRequestDTO,Model model){
        log.info("/boards 경로로 get 요청이 들어왔습니다.");

        model.addAttribute("response",boardServiceImpl.getList(pageRequestDTO));

        System.out.println(boardServiceImpl.getList(pageRequestDTO));
        return "board/boardList";
    }
}
