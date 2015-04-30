package io.pivotal.springcloud.consumerresttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsumerRestTemplateApplication {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String consume() {
        ProducerResponse response = restTemplate.getForObject("http://producer", ProducerResponse.class);

        return String.format("{\"value\": %s}", response.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRestTemplateApplication.class, args);
    }
}
