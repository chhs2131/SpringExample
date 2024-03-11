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
