package com.example.jpatutorial.repository;

import com.example.jpatutorial.domain.Board;
import com.example.jpatutorial.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByMember(Member member);
}
