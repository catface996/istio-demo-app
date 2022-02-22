package com.example.istio.api;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author by 大猫
 * @date 2022/2/22 8:44 PM
 * <p>
 * Copyright 2021  北京交个朋友数码科技有限公司, Inc. All rights reserved.
 */
@Component
public class DemoApiFallback implements FallbackFactory<DemoApi> {
    @Override
    public DemoApi create(Throwable cause) {
        return new DemoApi() {
            @Override
            public String sayHello() {
                return "error";
            }
        };
    }
}
