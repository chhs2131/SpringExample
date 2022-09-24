package com.example.membermanagementexample.api.member.domain;

import lombok.Data;import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
public class Member {
    private Long id;
    private String name;
}
