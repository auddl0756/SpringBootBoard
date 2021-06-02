package com.board.board.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_no")
    private Long boardNo;

    @Column(name="board_title",nullable = false)
    private String boardTitle;

    @Column(name="board_content",columnDefinition = "TEXT",nullable = false)
    private String boardContent;

    @Column(name="board_view",nullable = false)
    private Integer boardView;

    @Column(name="board_vote",nullable = false)
    private Integer boardVote;

    @Column(name="board_category",nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardCategory boardCategory;

    @Column(name="board_writer",nullable = false)
    private String boardWriter;

}
