package com.board.board.dto;

import com.board.board.entity.Member;
import com.board.board.entity.MemberRole;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.DateTimeException;
import java.time.LocalDate;

@Log4j2
@Data
public class MemberRegistrationDTO {
    @NotBlank(message = "아이디는 반드시 입력해야 합니다.")
    @Size(min=4,max=12,message = "아이디는 4글자 이상 12글자 이하만 가능합니다.")
    private String memberId;

    @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
    @Size(min=6,max=20,message = "비밀번호는 6글자 이상 20글자 이하만 가능합니다.")
    private String memberPwd;

    @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
    @Size(min=6,max=20,message = "비밀번호는 6글자 이상 20글자 이하만 가능합니다.")
    private String memberPwdConfirm;

    @NotBlank(message = "이메일은 반드시 입력해야 합니다.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String memberEmail;

    @NotBlank(message = "닉네임은 반드시 입력해야 합니다.")
    @Size(min=4,max=12,message = "닉네임은 4글자 이상 12글자 이하만 가능합니다.")
    private String memberNickname;

    @NotBlank(message = "생년월일은 반드시 입력해야 합니다.")
//    @DateTimeFormat(pattern = "yyMMdd")
    @Digits(integer = 6,fraction = 0,message = "주민번호앞자리를 입력해야 합니다.")
    private String memberBirth;

    public Member dtoToEntity(PasswordEncoder passwordEncoder){
        try{
            Member member = Member.builder()
                    .memberId(memberId)
                    .memberPwd(passwordEncoder.encode(memberPwd))
                    .memberEmail(memberEmail)
                    .memberNickname(memberNickname)
                    .memberBirth(convertBirth())
                    .memberRole(MemberRole.USER)
                    .memberScore(0)
                    .build();
            return member;
        }
        catch(DateTimeException e){
            log.error("생년월일 입력 오류");
            return null;
        }
    }

    public LocalDate convertBirth() throws DateTimeException {
        int year=Integer.parseInt(memberBirth.substring(0,2));
        int month=Integer.parseInt(memberBirth.substring(2,4));
        int date=Integer.parseInt(memberBirth.substring(4,6));

        return LocalDate.of(year,month,date);
    }
}
