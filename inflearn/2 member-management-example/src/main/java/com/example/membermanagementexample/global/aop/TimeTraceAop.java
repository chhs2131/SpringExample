package com.example.membermanagementexample.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
// Spring Bean 으로 등록해줘야 함
// @Component or bean 등록
public class TimeTraceAop {
    // 공통 관심 사항을 손쉽게 분리 가능 (+그에 따라 핵심관심사항(service)를 깔끔하게 관리가능)
    // AOP 사용시, 적용되는 클래스들은 프록시가 생성되어 해당 프록시를 통해 연결되게 된다.
    @Around("execution(* com.example.membermanagementexample..*(..))")  // 패키지 경로 기준으로 적용진행
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println(joinPoint.toString() + String.format("Method took %d ms", endTime - startTime));
        return result;
    }
}
