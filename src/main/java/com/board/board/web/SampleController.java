package com.board.board.web;

import com.board.board.service.SampleEntityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SampleController {
    @Autowired
    private SampleEntityService sampleEntityService;

    @GetMapping("/")
    public String home(){
        log.info("/ 경로로 get 요청이 들어왔습니다.");
        return "home";
    }

    @GetMapping("/sample")
    public String sample(Model model){
        log.info("/sample 경로로 get 요청이 들어왔습니다.");

        sampleEntityService.save(SampleDTO.builder().id(1L).data("sample data").build());

        model.addAttribute("result",sampleEntityService.findById(1L));

        return "sample";
    }
}
