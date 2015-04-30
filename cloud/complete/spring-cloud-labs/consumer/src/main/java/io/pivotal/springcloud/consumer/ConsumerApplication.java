package io.pivotal.springcloud.consumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsumerApplication {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String consume() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("PRODUCER", false);

        RestTemplate restTemplate = new RestTemplate();
        ProducerResponse response = restTemplate.getForObject(instance.getHomePageUrl(), ProducerResponse.class);

        return String.format("{\"value\": %s}", response.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
