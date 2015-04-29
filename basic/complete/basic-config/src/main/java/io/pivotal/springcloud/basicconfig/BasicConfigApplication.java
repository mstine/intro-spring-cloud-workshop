package io.pivotal.springcloud.basicconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BasicConfigApplication {

    @Value("${greeting}")
    private String greeting;

    @RequestMapping("/")
    public String home() {
        return String.format("%s World!", greeting);
    }

    public static void main(String[] args) {
        SpringApplication.run(BasicConfigApplication.class, args);
    }
}
