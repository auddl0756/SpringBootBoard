package com.board.board.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SampleAuthController {
    @GetMapping("/member")
    public void member(){
        log.info("/member 경로로 get 요청이 들어왔습니다.");
    }
}
