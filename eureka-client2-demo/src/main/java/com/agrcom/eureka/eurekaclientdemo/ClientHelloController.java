package com.agrcom.eureka.eurekaclientdemo;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientHelloController implements IGreetingController {

    @Autowired
    @Lazy
    EurekaClient eurekaClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @GetMapping("/serviceInfo/{applicationName}")
    public List<ServiceInstance> serviceInfo(@PathVariable String applicationName){
        return this.discoveryClient.getInstances(applicationName);
    }



}
