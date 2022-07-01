package com.example.jpatutorial.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long idx;
    private String name;
    private String email;
}

/*
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;
 */