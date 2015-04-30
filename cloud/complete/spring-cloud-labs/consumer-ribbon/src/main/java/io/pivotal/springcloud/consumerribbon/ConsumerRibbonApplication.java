package io.pivotal.springcloud.consumerribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsumerRibbonApplication {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/")
    public String consume() {
        ServiceInstance instance = loadBalancer.choose("producer");
        URI producerUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));

        RestTemplate restTemplate = new RestTemplate();
        ProducerResponse response = restTemplate.getForObject(producerUri, ProducerResponse.class);

        return String.format("{\"value\": %s}", response.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonApplication.class, args);
    }
}
