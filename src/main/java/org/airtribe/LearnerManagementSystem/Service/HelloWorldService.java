package org.airtribe.LearnerManagementSystem.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class HelloWorldService {
    public String helloWorld() {
        return "Hello World!";
    }
}
