package com.board.board.web;

import com.board.board.dto.MemberRegistrationDTO;
import com.board.board.entity.Member;
import com.board.board.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequestMapping("/register")
@Controller
public class RegistrationController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("dto",new MemberRegistrationDTO());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("dto") MemberRegistrationDTO dto, Errors errors){
        if(errors.hasErrors()){
            log.error("processRegistraion ,"+dto);
//            dto.reset();
            return "registration";
        }

        if(dto.getMemberPwd().equals(dto.getMemberPwdConfirm())==false){
            log.error("비밀번호와 확인 비밀번호 불일치");
            return "registration";
        }

        Member entity =dto.dtoToEntity(passwordEncoder);
        if(entity==null){
            return "registration";
        }

        memberRepository.save(entity);
        return "redirect:/login";
    }

//    @ResponseBody
//    @PostMapping("/duplication")
//    public ResponseEntity<?> checkIdDuplication(@RequestBody String memberId){
//        log.info("가입 아이디 중복 검사 요청 들어옴.");
//        return memberRepository.findByMemberId(memberId).isPresent()==false?
//                new ResponseEntity<>("사용가능한 아이디입니다!", HttpStatus.OK) :
//                new ResponseEntity<>("이미 사용 중인 아이디입니다!", HttpStatus.BAD_REQUEST);
//    }

    @ResponseBody
    @PostMapping("/duplication/id")
    public HttpStatus checkIdDuplication(@RequestBody String memberId){
        log.info("가입 아이디 중복 검사 요청 들어옴.");
        return memberRepository.findByMemberId(memberId).isPresent()==false? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @ResponseBody
    @PostMapping("/duplication/nickname")
    public HttpStatus checkNicknameDuplication(@RequestBody String memberNickname){
        log.info("가입 닉네임 중복 검사 요청 들어옴.");
        return memberRepository.findByMemberNickname(memberNickname).isPresent()==false? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}
