package com.board.board.config.auth.service;

import com.board.board.dto.MemberAuthDTO;
import com.board.board.entity.Member;
import com.board.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByMemberNickname(username);

        if(result.isPresent()==false){
            throw new UsernameNotFoundException(username+"라는 닉네임을 가진 유저는 존재하지 않습니다.");
        }

        Member member =result.get();
        List<GrantedAuthority> roleset = new ArrayList<>();
        roleset.add(new SimpleGrantedAuthority("ROLE_"+member.getMemberRole()));

        MemberAuthDTO memberAuthDTO = new MemberAuthDTO(
                member.getMemberNickname(),
                member.getMemberPwd(),
                roleset
        );

        return memberAuthDTO;
    }
}
