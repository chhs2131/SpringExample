package com.example.membermanagementexample.api.test.service;

import com.example.membermanagementexample.api.test.domain.Member;
import com.example.membermanagementexample.api.test.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    /**
     * test 작성시 given, when, then 구역으로 나누어서 작성하면, 나중에 살펴보기에 좋다.
     */
    private MemberService memberService;
    private MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);  // 외부에서 전달한 객체를 사용하며 이를 DI(Dependecny Injection)라고 함
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hi");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 회원가입시_중복회원예외() {
        // given
        Member member1 = new Member();  // 한번에 변수명 수정 단축키 shift + fn + F6
        member1.setName("hi");
        Member member2 = new Member();
        member2.setName("hi");

        // when
        memberService.join(member1);
        //        try {
        //            memberService.join(member2);
        //            fail();
        //        } catch (IllegalStateException e) {
        //            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. (이름중복)");
        //        }

        // 예외처리 테스트용 메소드  , comand + option + v
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. (이름중복)");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}