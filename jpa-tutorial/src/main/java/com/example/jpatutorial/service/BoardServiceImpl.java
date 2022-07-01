package com.example.jpatutorial.service;

import com.example.jpatutorial.domain.Board;
import com.example.jpatutorial.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> findBoardList(Pageable pageable) {
        // pageNumber 객체가 0 이하라면 0으로 초기화, 기본 페이지 크기인 10개 단위 만큼씩 게시글을 반환
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }

    public Board findBoardByIdx(Long idx) {
        return boardRepository.findById(idx).orElse(new Board());
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> updateBoard(Long idx, Board board) {
        Optional<Board> boardToUpdate = boardRepository.findById(idx);

        boardToUpdate.ifPresent(selectBoard ->{
            selectBoard.setTitle(board.getTitle());
            selectBoard.setContent(board.getContent());
            selectBoard.setBoardType(board.getBoardType());
            selectBoard.setMember(board.getMember());

            boardRepository.save(selectBoard);
        });

        return boardToUpdate;
    }

    public void deleteBoard(Long idx) {
        boardRepository.deleteById(idx);
    }
}
