package com.example.jpatutorial.jpa;

import com.example.jpatutorial.domain.Board;
import com.example.jpatutorial.domain.Member;
import com.example.jpatutorial.domain.enums.BoardType;
import com.example.jpatutorial.repository.BoardRepository;
import com.example.jpatutorial.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init() {
        Member member = memberRepository.save(Member.builder()
                .name("havi")
                .password("12345678")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build()
        );

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .content("내용")
                .boardType(BoardType.notice)
                .createdDate(LocalDateTime.now())
                .member(member)
                .build()
        );
    }

    @Test
    public void jpa_data_test() {
        Member member = memberRepository.findByEmail(email);
        assertThat(member.getName()).isEqualTo("havi");
        assertThat(member.getPassword()).isEqualTo("12345678");
        assertThat(member.getEmail()).isEqualTo(email);

        Board board = boardRepository.findByMember(member);
        assertThat(board.getTitle()).isEqualTo(boardTestTitle);
        assertThat(board.getContent()).isEqualTo("내용");
        assertThat(board.getBoardType()).isEqualTo(BoardType.notice);
    }
}
