package io.pivotal.springcloud.distconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class DistConfigApplication {

    @Autowired
    private Greeter greeter;

    @RequestMapping("/")
    public String home() {
        return String.format("%s World!", greeter.getGreeting());
    }

    public static void main(String[] args) {
        SpringApplication.run(DistConfigApplication.class, args);
    }
}
