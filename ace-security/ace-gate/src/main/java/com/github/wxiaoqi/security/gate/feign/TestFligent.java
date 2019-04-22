package com.github.wxiaoqi.security.gate.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MissLi
 * @create 2019/4/22 9:22
 * 远程服务负载均衡
 */
@FeignClient("ace-auth")
public interface TestFligent {
    @GetMapping("/testFeligent")
    String testFligen(String name);
}
