package com.study.user.sentineldemo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.common.utils.R;
import com.study.user.sentineldemo.common.ExceptionUtil;
import com.study.user.sentineldemo.domain.request.UserInfoReq;
import com.study.user.sentineldemo.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserFeignService userFeignService;

    @RequestMapping(value = "/findOrderByUserId")
    @SentinelResource(value = "findOrderByUserId",
            blockHandlerClass = ExceptionUtil.class,blockHandler = "handleException",
            fallbackClass = ExceptionUtil.class,fallback = "fallback")
    public R  findOrderByUserId(@RequestBody UserInfoReq request) {

        //feign调用
        R result = userFeignService.findOrderByUserId(request);

        return result;
    }

    @SentinelResource(value = "hot")
    @RequestMapping("/hot")
    public String hot(String a,String b){
        return a+b;
    }


    AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping("/test2")
    public String test2() {
        atomicInteger.getAndIncrement();
        if (atomicInteger.get() % 2 == 0){
            //模拟异常和异常比率
            int i = 1/0;
        }

        return "========test2()";
    }

}
