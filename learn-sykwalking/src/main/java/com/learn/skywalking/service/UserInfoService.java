package com.learn.skywalking.service;

import com.learn.skywalking.entity.UserDO;

import java.util.List;

/**
 * @author ChenYP
 */
public interface UserInfoService {


    List<UserDO> queryUserInfo(Integer id);
}
