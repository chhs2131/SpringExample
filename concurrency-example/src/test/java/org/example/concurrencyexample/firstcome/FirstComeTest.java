package org.example.concurrencyexample.firstcome;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 선착순 당첨 이벤트! (e.g. 수강신청)
 */
public class FirstComeTest {
    private static final int TIME_OUT = 3;
    private ExecutorService executorService;
    private Prizes prizes;

    @BeforeEach
    void setup() {
        prizes = new Prizes(new ArrayList<>());
    }

    private boolean tryToWin(int participantNumber) {
        try {
            String participantName = String.valueOf(participantNumber);
            prizes.addWinner(participantName);

            System.out.println("축하합니다. " + participantName + "님, 당첨되셨습니다!");
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    @Test
    void 천명이동시에도전_그중10명이당첨_실패() throws Exception {
        final int maxWinners = 10;
        final int threadCount = 1000;
        executorService = Executors.newFixedThreadPool(threadCount);
        prizes.init(maxWinners);
        final List<String> winners = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            final int num = i;
            executorService.execute(() -> {
                if (tryToWin(num)) winners.add(String.valueOf(num));
            });
        }
        executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

        Assertions.assertThat(prizes.getCountWinners()).isEqualTo(maxWinners);
        Assertions.assertThat(winners.size()).isNotEqualTo(maxWinners);
    }

    @Test
    void 천명이동시에도전_그중10명이당첨_synchronized_성공() throws Exception {
        final int maxWinners = 10;
        final int threadCount = 1000;
        executorService = Executors.newFixedThreadPool(threadCount);
        prizes.init(maxWinners);
        final List<String> winners = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            final int num = i;
            executorService.execute(() -> {
                synchronized (this) {
                    if (tryToWin(num))
                        winners.add(String.valueOf(num));
                }
            });
        }
        executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

        Assertions.assertThat(prizes.getCountWinners()).isEqualTo(maxWinners);
        Assertions.assertThat(winners).hasSize(maxWinners);
    }

    @Test
    void 천명이동시에도전_그중10명이당첨_reentrantLock_성공() throws Exception {
        final Lock lock = new ReentrantLock();
        final int maxWinners = 10;
        final int threadCount = 1000;
        executorService = Executors.newFixedThreadPool(threadCount);
        prizes.init(maxWinners);
        final List<String> winners = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            final int num = i;
            executorService.execute(() -> {
                lock.lock();
                try {
                    if (tryToWin(num))
                        winners.add(String.valueOf(num));
                } finally {
                    lock.unlock();
                }
            });
        }
        executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

        Assertions.assertThat(prizes.getCountWinners()).isEqualTo(maxWinners);
        Assertions.assertThat(winners).hasSize(maxWinners);
    }

    @Test
    void 천명이동시에도전_그중10명이당첨_concurrentList_실패() throws Exception {
        prizes = new Prizes(new CopyOnWriteArrayList<>());
        final int maxWinners = 10;
        final int threadCount = 1000;
        executorService = Executors.newFixedThreadPool(threadCount);
        prizes.init(maxWinners);
        final List<String> winners = new CopyOnWriteArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            final int num = i;
            executorService.execute(() -> {
                if (tryToWin(num)) winners.add(String.valueOf(num));
            });
        }
        executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

        Assertions.assertThat(prizes.getCountWinners()).isNotEqualTo(maxWinners);
        Assertions.assertThat(winners.size()).isNotEqualTo(maxWinners);
    }
}
