package com.example.istio.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by 大猫
 * @date 2022/2/23 11:47 AM
 * <p>
 * Copyright 2021  北京交个朋友数码科技有限公司, Inc. All rights reserved.
 */
@Configuration
public class FeignSupportConfig {
    /**
     * feign请求拦截器
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignInterceptor();
    }

}
