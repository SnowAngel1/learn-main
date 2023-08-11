package com.learn.skywalking.dao;

import com.learn.skywalking.entity.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYP
 */
public interface UserMapper {

    /**
     * 查询用户信息
     * @return
     */
    List<UserDO> selectUserInfo(@Param("id")Integer id);
}
