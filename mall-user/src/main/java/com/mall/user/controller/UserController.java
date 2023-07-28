package com.mall.user.controller;

import com.common.utils.R;
import com.mall.user.domain.request.user.UserInfoReq;
import com.mall.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "/get/user")
    public R queryUserInfo(@RequestBody UserInfoReq request){
        return R.ok().put("user",userInfoService.queryUserInfo(request));
    }
}
