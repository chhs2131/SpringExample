package org.example.concurrencyexample.firstcome;

import java.util.Collections;
import java.util.List;
import org.springframework.util.Assert;

public class Prizes {
    private final List<String> winners;
    private int maxWinners;

    public Prizes(List<String> winners) {
        this.winners = winners;
    }

    public void init(int maxWinners) {
        Assert.isTrue(maxWinners >= 0, "최대 당첨 인원은 음수가 될 수 없습니다.");
        this.maxWinners = maxWinners;
    }

    public int getCountWinners() {
        return winners.size();
    }

    public void addWinner(String name) {
        Assert.notNull(name, "이름은 null 값이 올 수 없습니다.");
        Assert.isTrue(!name.isEmpty(), "이름은 빈 값이 올 수 없습니다.");

        delay(100);  // 꽤나 복잡한 상품 당첨 로직

        if (!(winners.size() < maxWinners)) {
            throw new IllegalStateException("상품이 모두 소진되었습니다.");
        }
        winners.add(name);
    }

    private static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        Collections.sort(winners, (w1, w2) -> {
            int a1 = Integer.valueOf(w1);
            int a2 = Integer.valueOf(w2);

            return a1 - a2;
        });

        return winners.toString();
    }
}
