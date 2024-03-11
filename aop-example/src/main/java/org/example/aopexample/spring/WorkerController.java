package org.example.aopexample.spring;

import org.example.aopexample.job.Cooker;
import org.example.aopexample.job.Programmer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
