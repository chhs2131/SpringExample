package com.example.jpatutorial.service;

import com.example.jpatutorial.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardService {
    public Page<Board> findBoardList(Pageable pageable);
    public Board findBoardByIdx(Long idx);
    public Board createBoard(Board board);
    public Optional<Board> updateBoard(Long idx, Board board);
    public void deleteBoard(Long idx);
}
