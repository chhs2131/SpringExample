package com.mgon.customresponse.api.member;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    @GetMapping("")
    public String thereIsNoMember() {
        throw new IllegalStateException("MemberController is not available");
    }

    @GetMapping("/{memberId}")
    public MemberDto findMemberById(@PathVariable("memberId") String memberId) {
        return MemberDto.builder()
                .id(memberId)
                .phone("010-1234-5678")
                .email("mgon@basak.com")
                .country("KR")
                .build();
    }
}
