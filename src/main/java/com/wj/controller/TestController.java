package com.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.wj.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangjun
 * @date 18-7-2 下午6:13
 * @description
 * @modified by
 */

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private static final String serviceName = "consul-miya";

    @RequestMapping("/test")
    @HystrixCommand(fallbackMethod = "commonError")
    public Object test() {
        throw new RuntimeException("11111");
        /*String str = getUrl("api/hello");
        System.out.println(str);
        String url = "http://" + serviceName + "/api/hello";
        String result = restTemplate.getForObject(url, String.class);
        restTemplate.postForEntity(url, RestTemplateUtil.postJson(""), String.class);
        return result;*/
    }
    @RequestMapping("/testPost")
    public Object testPost(@RequestBody JSONObject jsonObject) {
        /*String str = getUrl("api/hello");
        System.out.println(str);*/
        String url = "http://" + serviceName + "/api/post";
        ResponseEntity<JSONObject> result = restTemplate.postForEntity(url, RestTemplateUtil.postJson(jsonObject.toJSONString()), JSONObject.class);
        return result.getBody();
    }


    private String getUrl(String apiName) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceName);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        if (apiName.startsWith("/")) {
            url = url + apiName;
        }
        else {
            url = url + "/" + apiName;
        }
        return url;
    }
}
