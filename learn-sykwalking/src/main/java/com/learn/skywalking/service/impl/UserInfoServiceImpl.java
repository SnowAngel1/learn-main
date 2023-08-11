package com.learn.skywalking.service.impl;

import com.learn.skywalking.dao.UserMapper;
import com.learn.skywalking.entity.UserDO;
import com.learn.skywalking.service.UserInfoService;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYP
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserMapper userMapper;

    @Override
    @Trace
    @Tags({@Tag(key = "param",value = "arg[0]"),@Tag(key = "queryUserInfo",value = "returnedObj")})
    public List<UserDO> queryUserInfo(Integer id) {
        return userMapper.selectUserInfo(id);
    }
}
