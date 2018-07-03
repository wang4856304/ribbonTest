package com.wj.config;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangjun
 * @date 18-7-2 下午7:47
 * @description
 * @modified by
 */

@Configuration
public class LoadBalancedConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 随机策略
     * @return
     */
    @Primary
    @Bean
    public IRule ribbonRandomRule() {
        return new RandomRule();
    }

    /**
     * 最小并发数策略
     * @return
     */
    @Bean
    public IRule ribbonBestAvailableRule() {
        return new BestAvailableRule();
    }

    /**
     * 过滤掉那些因为一直连接失败的被标记为circuit tripped的后端server，
     * 并过滤掉那些高并发的的后端server（active connections 超过配置的阈值）
     * @return
     */
    @Bean
    public IRule ribbonAvailabilityFilteringRule() {
        return new AvailabilityFilteringRule();
    }

    /**
     * 根据响应时间分配一个weight，响应时间越长，weight越小，被选中的可能性越低
     * @return
     */
    @Bean
    public IRule ribbonWeightedResponseTimeRule() {
        return new WeightedResponseTimeRule();
    }

    /**
     * 对选定的负载均衡策略机上重试机制。
     * @return
     */
    @Bean
    public IRule ribbonRetryRule() {
        return new RetryRule();
    }

    /**
     * roundRobin方式轮询选择server
     * @return
     */
    @Bean
    public IRule ribbonRoundRobinRule() {
        return new RoundRobinRule();
    }

    /**
     * 复合判断server所在区域的性能和server的可用性选择server
     * @return
     */
    @Bean
    public IRule ribbonZoneAvoidanceRule() {
        return new ZoneAvoidanceRule();
    }
}
