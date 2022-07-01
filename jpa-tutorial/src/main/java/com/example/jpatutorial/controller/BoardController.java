package com.example.jpatutorial.controller;

import com.example.jpatutorial.domain.Board;
import com.example.jpatutorial.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @RequestMapping(value="/{idx}", method = RequestMethod.GET)
    public ResponseEntity<Board> getBoard(@PathVariable long idx) {
        return ResponseEntity.ok(boardService.findBoardByIdx(idx));
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<Page<Board>> getBoards(@PageableDefault Pageable pageable) throws Exception {
        return ResponseEntity.ok(boardService.findBoardList(pageable));
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.createBoard(board));
    }

    @RequestMapping(value="/{idx}", method = RequestMethod.PUT)
    public ResponseEntity<Optional<Board>> updateBoard(@PathVariable long idx, @RequestBody Board board) {
        return ResponseEntity.ok(boardService.updateBoard(idx, board));
    }

    @RequestMapping(value="/{idx}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBoard(@PathVariable long idx) {
        boardService.deleteBoard(idx);
        return ResponseEntity.ok(new String("삭제성공"));
    }
}
