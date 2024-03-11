# AOP 예시
관점 지향 프로그래밍 예제입니다. 순사 자바 Proxy에 대해 먼저 소개하고, Spring이 제공하는 AOP에 대해 살펴봅니다.


</br>

## 순수 자바의 Proxy 
```java
package org.example.aopexample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.example.aopexample.job.Worker;

public class WorkerProxy {
    private Worker worker;

    public WorkerProxy(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return (Worker) Proxy.newProxyInstance(
            Worker.class.getClassLoader(),
            new Class[]{Worker.class},
            workerHandler()
        );
    }

    private InvocationHandler workerHandler() {
        return (proxy, method, args) -> {
            System.out.println("====================================================================================");
            System.out.println("관리자: 일해라! 이놈아!");
            long start = System.currentTimeMillis();

            Object result = method.invoke(worker, args);

            long end = System.currentTimeMillis();
            System.out.println("관리자: 일하는데 " + (double) (end-start)/1000 + "초가 걸렷네!!");
            System.out.println("====================================================================================");
            return result;
        };
    }
}
```


</br>

### 실행결과
```javascript
요리사: 요리을 합니다.
요리사: 요리완료!
====================================================================================
관리자: 일해라! 이놈아!
개발자: 코딩을 합니다.
개발자: 코딩완료!
관리자: 일하는데 1.005초가 걸렷네!!
====================================================================================
====================================================================================
관리자: 일해라! 이놈아!
요리사: 요리을 합니다.
요리사: 요리완료!
관리자: 일하는데 1.501초가 걸렷네!!
====================================================================================
```

</br>
</br>
</br>

## 스프링 AOP

### 의존성 추가
```groovy
dependencies {
    /* 스프링 AOP 사용을 위해 추가 */
    implementation 'org.springframework.boot:spring-boot-starter-aop'
}
```


</br>

### Controller
```java
@RestController
public class WorkerController {
    @GetMapping("/cook")
    void cook() {
        final Cooker cooker = new Cooker();
        cooker.work();
    }

    @GetMapping("/coding")
    void coding() {
        final Programmer programmer = new Programmer();
        programmer.work();
    }
}
```

</br>

### AOP Class
```java
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
```

</br>

### 실행결과
```javascript
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.3)

2024-03-12T02:04:49.750+09:00  INFO 12397 --- [aop-example] [           main] o.e.aopexample.AopExampleApplication     : Starting AopExampleApplication using Java 17.0.4.1 with PID 12397 (/Users/hyeon/Documents/GitHub/SpringExample/aop-example/build/classes/java/main started by hyeon in /Users/hyeon/Documents/GitHub/SpringExample/aop-example)
2024-03-12T02:04:49.752+09:00  INFO 12397 --- [aop-example] [           main] o.e.aopexample.AopExampleApplication     : The following 1 profile is active: "local"
2024-03-12T02:04:50.091+09:00  INFO 12397 --- [aop-example] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-03-12T02:04:50.095+09:00  INFO 12397 --- [aop-example] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-03-12T02:04:50.095+09:00  INFO 12397 --- [aop-example] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.19]
2024-03-12T02:04:50.112+09:00  INFO 12397 --- [aop-example] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-03-12T02:04:50.112+09:00  INFO 12397 --- [aop-example] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 344 ms
2024-03-12T02:04:50.256+09:00  INFO 12397 --- [aop-example] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-03-12T02:04:50.262+09:00  INFO 12397 --- [aop-example] [           main] o.e.aopexample.AopExampleApplication     : Started AopExampleApplication in 0.631 seconds (process running for 4.865)
2024-03-12T02:04:53.217+09:00  INFO 12397 --- [aop-example] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-03-12T02:04:53.217+09:00  INFO 12397 --- [aop-example] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-03-12T02:04:53.219+09:00  INFO 12397 --- [aop-example] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
====================================================================================
관리자: 일해라! 이놈아!cook
요리 재료를 준비합니다...
요리사: 요리을 합니다.
요리사: 요리완료!
관리자: 일하는데 1.505초가 걸렷네!!cook
====================================================================================
====================================================================================
관리자: 일해라! 이놈아!coding
개발자: 코딩을 합니다.
개발자: 코딩완료!
리팩토링을 진행합니다...
관리자: 일하는데 1.005초가 걸렷네!!coding
====================================================================================
```

