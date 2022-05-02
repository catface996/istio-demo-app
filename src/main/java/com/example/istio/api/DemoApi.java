package com.example.istio.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author by 大猫
 * @date 2022/2/22 8:38 PM
 * <p>
 * Copyright 2021  catface996, Inc. All rights reserved.
 */
@FeignClient(url = "${service.url}", name = "demoApi", fallbackFactory = DemoApiFallback.class)
public interface DemoApi {

    /**
     * 打招呼接口
     *
     * @return 打招呼的回应
     */
    @GetMapping(value = "/sayHello")
    String sayHello();
}
