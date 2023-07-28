package com.mall.user.service.impl;

import com.mall.user.dao.user.UserMapper;
import com.mall.user.domain.entity.user.UserInfoBean;
import com.mall.user.domain.request.user.UserInfoReq;
import com.mall.user.domain.response.user.UserInfoResp;
import com.mall.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserInfoResp queryUserInfo(UserInfoReq request) {
        UserInfoBean userInfoBean = userMapper.selectUserInfo(request);
        UserInfoResp userInfo = new UserInfoResp();
        BeanUtils.copyProperties(userInfoBean,userInfo);
        return userInfo;
    }
}
