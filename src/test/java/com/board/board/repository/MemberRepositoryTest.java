package com.board.board.repository;

import com.board.board.entity.Member;
import com.board.board.entity.MemberRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 저장후_출력(){
        String sampleId= "sampleId";
        String sampleEmail ="sampleEmail";
        String sampleNickname= "sampleNickname";
        LocalDate sampleBirth = LocalDate.of(1970,10,10);
        String samplePwd = "samplePwd";
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

        Member latestMember = memberRepository.findAll().get(0);
        String encodedPwd = passwordEncoder.encode(samplePwd);

        assertThat(latestMember.getMemberId()).isEqualTo(sampleId);
        assertThat(passwordEncoder.matches(samplePwd,encodedPwd)).isTrue();

        System.out.println(member);

    }
}