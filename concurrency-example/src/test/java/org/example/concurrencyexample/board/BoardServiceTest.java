package org.example.concurrencyexample.board;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTest {
    @Autowired
    private BoardService boardService;
    private static final int TIME_OUT = 5;

    @Test
    void 백명이동시에조회_예상조회수100_성공() throws Exception {
        final Long boardId = 1L;
        final int threadCount = 100;
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        게시글생성요청_생성();

        for (int i = 0; i < threadCount; i++) {
            service.execute(() -> boardService.getBoard(boardId));
        }
        service.awaitTermination(10, TimeUnit.SECONDS);
        final Board board = boardService.getBoard(boardId);

        Assertions.assertThat(board.getCount()).isEqualTo(101);
    }

    private void 게시글생성요청_생성() {
        final BoardRequest request = new BoardRequest("테스트게시글", "테스트내용123");
        boardService.write(request);
    }
}