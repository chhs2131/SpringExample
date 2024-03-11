package org.example.aopexample;

import org.example.aopexample.job.Cooker;
import org.example.aopexample.job.Programmer;
import org.example.aopexample.job.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopExampleApplication {

    public static void main(String[] args) {
        // 순수 자바 Proxy 동작
        javaWorker();

        // SpringWeb AOP 동작
        SpringApplication.run(AopExampleApplication.class, args);
    }

    private static void javaWorker() {
        // 프록시를 사용하지 않은 경우
        final Worker cooker = new Cooker();
        cooker.work();

        // 프록시를 사용한 경우1
        final WorkerProxy programmerProxy = new WorkerProxy(new Programmer());
        final Worker programmer = programmerProxy.getWorker();
        programmer.work();

        // 프록시를 사용한 경우2
        final WorkerProxy cookerProxy = new WorkerProxy(new Cooker());
        final Worker newCooker = cookerProxy.getWorker();
        newCooker.work();
    }
}
