package org.airtribe.LearnerManagementSystem.Controller;

import org.airtribe.LearnerManagementSystem.Service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;
    @GetMapping("/")
    public String helloWorld() {
        return helloWorldService.helloWorld();
    }

    @GetMapping("/hello")
    public String hello() {
        return  "hello from Spring Boot!";
    }
}
