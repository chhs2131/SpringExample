package com.example.membermanagementexample.api.member.repository;

import com.example.membermanagementexample.api.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemberRepositoryTest {
    @Autowired
    MemberService memberService;

//    @Autowired
//    JpaMemberRepository jpaMemberRepository;

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findAll() {
    }
}