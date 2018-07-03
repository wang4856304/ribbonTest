package com.wj.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author wangjun
 * @date 18-7-2 下午8:11
 * @description
 * @modified by
 */
public class RestTemplateUtil {

    public static HttpEntity<String> postJson(String params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(params, headers);
        return entity;
    }
}
