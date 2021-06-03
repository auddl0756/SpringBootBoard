package com.board.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_no")
    private Long memberNo;

    @Column(name="member_id",nullable = false)
    private String memberId;

    @Column(name="member_pwd",nullable = false)
    private String memberPwd;

    @Column(name="member_email",nullable = false)
    private String memberEmail;

    @Column(name="member_nickname",nullable = false)
    private String memberNickname;

    @Column(name="member_score",nullable = false)
    private Integer memberScore;

    @Column(name="member_profile")
    private String memberProfile;

    @Column(name="member_birth",nullable = false)
    private LocalDateTime memberBirth;

    @Enumerated(EnumType.STRING)
    @Column(name="member_role",nullable = false)
    private MemberRole memberRole;

}
