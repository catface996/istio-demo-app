package com.example.istio.config;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author by 大猫
 * @date 2022/2/23 11:44 AM
 * <p>
 * Copyright 2021  北京交个朋友数码科技有限公司, Inc. All rights reserved.
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    private static final String ENV = "env";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, String> headers = getHeaders(getHttpServletRequest());
        for (String headerName : headers.keySet()) {
            requestTemplate.header(headerName, getHeaders(getHttpServletRequest()).get(headerName));
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            if (needThisHeader(key)) {
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

    private boolean needThisHeader(String headerName) {
        if (ENV.equals(headerName)) {
            return true;
        }
        return false;
    }
}
