package com.ssb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api")
public class TestController {

    @GetMapping(value = "/test")
    private String test(){
        return "这里是WEB端";
    }
}
