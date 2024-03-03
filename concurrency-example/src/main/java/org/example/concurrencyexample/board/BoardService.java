package org.example.concurrencyexample.board;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardService {
    private final BoardRepository boardRepository;
    private static final Lock lock = new ReentrantLock();

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/{id}")
    @Transactional
    public Board getBoard(@PathVariable("id") final Long id) {
        final Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 게시글이 없습니다."));

        board.increaseViewCount();
        boardRepository.save(board);

// 트랜잭션이 외부로 전파되야 하기 때문에 JVM Level에서의 Lock은 의미가 없어진다.
//        lock.lock();
//        try {
//            board.increaseViewCount();
//            boardRepository.save(board);
//        } finally {
//            lock.unlock();
//        }

        return board;
    }

    @PostMapping
    public Board write(@RequestBody BoardRequest request) {
        final Board board = new Board(request.title(), request.content());
        return boardRepository.save(board);
    }
}
