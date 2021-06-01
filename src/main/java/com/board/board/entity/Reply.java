package com.board.board.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reply extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reply_no")
    private Long replyNo;

    @Column(name="reply_content",nullable = false)
    private String replyContent;

    @Column(name="reply_selected")
    @Enumerated(EnumType.STRING)
    private SelectType replySelected;

    @Column(name="reply_vote",nullable = false)
    private Integer replyVote;

    @Column(name="reply_writer",nullable = false)
    private String replyWriter;

    @Column(name="reply_parent")
    private Long replyParent;

    //연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

}
