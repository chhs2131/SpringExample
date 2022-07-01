package com.example.jpatutorial.dto;

import com.example.jpatutorial.domain.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private Long idx;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private Member member;
}



/*

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본키가 자동으로 할당되도록 설정
    private Long idx;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)  // Enum 타입 매핑, DB와 JAVA 간의 Enum 매핑을 지원
    private BoardType boardType;

    @Column
    private LocalDateTime createdDate;

    @OneToOne(fetch = FetchType.LAZY)  // Board 와 Member Domain(Entity)를 1:1 관계로 설정하며, Member Entity 의 PK(idx)가 저장된다.
    private Member member;

 */