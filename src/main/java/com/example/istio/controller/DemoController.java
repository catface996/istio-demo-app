package com.example.istio.controller;

import com.example.istio.api.DemoApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by 大猫
 * @date 2022/2/22 5:43 PM
 * <p>
 * Copyright 2021  北京交个朋友数码科技有限公司, Inc. All rights reserved.
 */
@Slf4j
@RestController
public class DemoController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${next}")
    private Boolean next;

    @Value("${env}")
    private String env;

    private DemoApi demoApi;

    @ResponseBody
    @GetMapping(value = "/sayHello")
    public String sayHello() {
        String current = "I'm " + appName + "(" + env + ")";
        log.info(current);
        String response = "";
        if (next) {
            response = demoApi.sayHello();
        }
        return current + " --> " + response;
    }

    @Autowired
    public void setDemoApi(DemoApi demoApi) {
        this.demoApi = demoApi;
    }
}
