package org.example.aopexample.job;

public class Cooker implements Worker {
    private final String job = "요리";

    @Override
    public void work() {
        System.out.println("요리사: " + job + "을 합니다.");
        try {
            Thread.sleep(1500);
            System.out.println("요리사: " + job + "완료!");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("요리사: " + job + "수행 중 문제가 발생했습니다.");
        }
    }
}
