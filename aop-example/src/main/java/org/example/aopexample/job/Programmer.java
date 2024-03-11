package org.example.aopexample.job;

public class Programmer implements Worker {
    private final String job = "코딩";

    @Override
    public void work() {
        System.out.println("개발자: " + job + "을 합니다.");
        try {
            Thread.sleep(1000);
            System.out.println("개발자: " + job + "완료!");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("개발자: " + job + "수행 중 문제가 발생했습니다.");
        }
    }
}
