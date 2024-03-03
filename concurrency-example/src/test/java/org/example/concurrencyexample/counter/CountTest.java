package org.example.concurrencyexample.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 단순히 조회수가 증가함
 */
public class CountTest {
    private static final int TIME_OUT = 7;
    private ExecutorService executorService;
    private Counter counter;
    private Lock lock;

    @BeforeEach
    void setup() {
        counter = new Counter();
    }

    @Test
    void originalTest_Fail() throws Exception {
        int loop = 100;
        executorService = Executors.newFixedThreadPool(loop);

        for (int i = 0; i < loop; i++) {
            executorService.execute(() -> counter.increase());
        }
        executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

        Assertions.assertThat(counter.getCount()).isNotEqualTo(loop);
    }

    @Test
    void synchronizedTest_Success() throws Exception {
        int loop = 100;
        executorService = Executors.newFixedThreadPool(loop);

        for (int i = 0; i < loop; i++) {
            executorService.execute(() -> synchronizedIncrease());
        }
        executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

        Assertions.assertThat(counter.getCount()).isEqualTo(loop);
    }

    // or use this method
    synchronized void synchronizedIncrease() {
        counter.increase();
    }

    @Test
    void lockTest_Success() throws Exception {
        int loop = 100;
        executorService = Executors.newFixedThreadPool(loop);
        lock = new ReentrantLock();

        for (int i = 0; i < loop; i++) {
            executorService.execute(() -> lockIncrease());
        }
        executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

        Assertions.assertThat(counter.getCount()).isEqualTo(loop);
    }

    void lockIncrease() {
        lock.lock();
        try {
            counter.increase();
        } finally {
            lock.unlock();
        }
    }
}
