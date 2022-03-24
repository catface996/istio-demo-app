package com.example.istio.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import brave.Tracer;
import com.example.istio.api.DemoApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by 大猫
 * @date 2022/2/22 5:43 PM catface996 出品
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

    @Autowired
    private Tracer tracer;

    @ResponseBody
    @GetMapping(value = "/sayHello")
    public String sayHello(HttpServletRequest request) {
        String headerEnv = request.getHeader("env");
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            log.info("{} -- > {}", headerName, request.getHeader(headerName));
        }
        String headerInfo = "header env(" + headerEnv + ")";
        String current = headerInfo + ", I'm " + appName + "(" + env + ")";
        log.info(current);
        String response = "";
        if (next) {
            response = demoApi.sayHello();
        }
        String traceId = tracer.currentSpan().context().traceIdString();
        return "TraceId: " + traceId + "  " + current + " --> " + response;
    }

    @Autowired
    public void setDemoApi(DemoApi demoApi) {
        this.demoApi = demoApi;
    }
}
