package com.board.board.repository;

import com.board.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("select m from Member m where m.memberNickname = :memberNickname")
    Optional<Member> findByMemberNickname(String memberNickname);
}
