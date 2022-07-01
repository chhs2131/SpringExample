package com.example.jpatutorial.domain;

import com.example.jpatutorial.domain.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable {

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

    @Builder
    public Board(String title, String content, BoardType boardType, LocalDateTime createdDate, Member member){
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.createdDate = createdDate;
        this.member = member;
    }

}
