package com.agrcom.eureka.eurekaclientdemo;

import org.springframework.web.bind.annotation.GetMapping;

public interface IGreetingController {
    @GetMapping("/greeting")
    String greeting();
}
