package com.learn.skywalking.controller;

import com.learn.skywalking.entity.UserDO;
import com.learn.skywalking.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ChenYP
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/helloSkyWalking/{id}")
    public List<UserDO> getUser(@PathVariable("id")Integer id){
        return userInfoService.queryUserInfo(id);
    }


    @RequestMapping(value = "/info/{id}")
    public List<UserDO> info(@PathVariable("id")Integer id){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userInfoService.queryUserInfo(id);
    }

    @RequestMapping("/notify")
    public String notify(@RequestBody Object obj){
        //TODO 告警信息，给技术负责人发短信，钉钉消息，邮件，微信通知等
        System.err.println(obj.toString());
        return "notify successfully";
    }

}
