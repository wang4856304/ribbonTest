package com.wj.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjun
 * @date 18-7-3 上午11:43
 * @description
 * @modified by
 */
public abstract class BaseController {

    private static Log log = LogFactory.getLog(BaseController.class);

    //private static Logger log = LoggerFactory.getLogger(BaseController.class);

    public Object commonError(Throwable throwable) {
        log.error("1111", throwable);
        //throw new RuntimeException(throwable);
        return "error";
    }

    @ExceptionHandler
    public Object handleException(Exception e) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("-1", "系统异常");
        return map;
    }
}
