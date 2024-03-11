package org.example.aopexample;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WorkerAspect {

    // WorkerController의 cook 메소드를 실행하기 전에 실행
    @Before("execution(* org.example.aopexample.spring.WorkerController.cook(..))")
    public void beforeCooking() {
        System.out.println("요리 재료를 준비합니다...");
    }

    // WorkerController의 coding 메소드를 실행한 후에 실행
    @After("execution(* org.example.aopexample.spring.WorkerController.coding(..))")
    public void afterCoding() {
        System.out.println("리팩토링을 진행합니다...");
    }

    // WorkerController에 있는 모든 메소드 실행에 AOP를 지정
    @Around("execution(* org.example.aopexample.spring.WorkerController.*(..))")
    public Object aroundWorkerActivities(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("====================================================================================");
        System.out.println("관리자: 일해라! 이놈아!" + joinPoint.getSignature().getName());
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed(); // Proceed with the original method call

        long end = System.currentTimeMillis();
        System.out.println("관리자: 일하는데 " + (double) (end-start)/1000 + "초가 걸렷네!!" + joinPoint.getSignature().getName());
        System.out.println("====================================================================================");
        return result;
    }
}
