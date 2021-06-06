package com.board.board;

import com.board.board.entity.Member;
import com.board.board.entity.MemberRole;
import com.board.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BoardApplication implements CommandLineRunner {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sampleId= "si";
        String sampleEmail ="se";
        String sampleNickname= "sn";
        LocalDate sampleBirth = LocalDate.of(1970,10,10);
        String samplePwd = "sp";
        MemberRole sampleRole = MemberRole.USER;

        Member member = Member.builder()
                .memberId(sampleId)
                .memberEmail(sampleEmail)
                .memberNickname(sampleNickname)
                .memberBirth(sampleBirth)
                .memberPwd(passwordEncoder.encode(samplePwd))
                .memberRole(sampleRole)
                .memberScore(0)
                .build();

        memberRepository.save(member);
    }
}
