package com.example.membermanagementexample.repository;

import com.example.membermanagementexample.api.member.domain.Member;
import com.example.membermanagementexample.api.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    /*
    Test 순서는 보장되지 않는다. (즉 순서에 의존되면 안된다)
    따라서 각 테스트가 끝날 떄 마다 데이터를 초기화 해줘야된다.

    여러명이서 개발하게 된다면 테스트코드 없이 개발하는건 사실상 불가능하다.
     */
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 메서드가 실행이 끝날때마다 자동으로 실행되는 call back method
     * 여기서는 각 테스트 종료시마다 store 를 비운다.
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("String");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        // Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("String1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("String2");
        repository.save(member2);

        Member result = repository.findByName("String1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {

        Member member1 = new Member();
        member1.setName("String1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("String2");
        repository.save(member2);
        Member member3 = new Member();
        member3.setName("String3");
        repository.save(member3);
        Member member4 = new Member();
        member4.setName("String4");
        repository.save(member4);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(4);
    }
}
