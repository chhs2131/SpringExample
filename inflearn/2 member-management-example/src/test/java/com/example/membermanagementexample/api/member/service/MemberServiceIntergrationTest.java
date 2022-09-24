package com.example.membermanagementexample.api.member.service;

import com.example.membermanagementexample.api.member.domain.Member;
import com.example.membermanagementexample.api.member.repository.JpaMemberRepository;
import com.example.membermanagementexample.api.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceIntergrationTest {
    @Autowired
    MemberService memberService;

    @Autowired
    JpaMemberRepository memberRepository;

    @Test
    public void join() {
        // given
        Member member = new Member();
        member.setName("John");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }
}
