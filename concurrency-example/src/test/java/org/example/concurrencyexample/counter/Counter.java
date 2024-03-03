package org.example.concurrencyexample.counter;

public class Counter {
    private static final int DELAY_TIME = 50;
    private int count;

    public int increase() {
        delay(DELAY_TIME);
        return count++;
    }

    public int getCount() {
        return count;
    }

    public int reset() {
        return count = 0;
    }

    private static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
