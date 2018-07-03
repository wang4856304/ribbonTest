package com.wj;

import com.netflix.loadbalancer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangjun
 * @date 18-7-2 下午5:46
 * @description
 * @modified by
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class RibbonApplication {

    public static void main(String args[]) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}
