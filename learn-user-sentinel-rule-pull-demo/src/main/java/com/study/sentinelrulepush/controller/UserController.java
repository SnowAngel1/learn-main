package com.study.sentinelrulepush.controller;

import com.common.utils.R;
import com.study.sentinelrulepush.domain.request.UserInfoReq;
import com.study.sentinelrulepush.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
//    @SentinelResource(value = "findOrderByUserId",
//            blockHandlerClass = ExceptionUtil.class,blockHandler = "handleException",
//            fallbackClass = ExceptionUtil.class,fallback = "fallback")
    public R  findOrderByUserId(@RequestBody UserInfoReq request) {
        //feign调用
        R result = userFeignService.findOrderByUserId(request);
        return result;
    }
    
    

}
