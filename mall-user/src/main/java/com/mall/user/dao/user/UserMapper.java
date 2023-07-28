package com.mall.user.dao.user;

import com.mall.user.domain.entity.user.UserInfoBean;
import com.mall.user.domain.request.user.UserInfoReq;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
public interface UserMapper {


    /**
     * 获取用户信息
     * @param request
     * @return
     */
    UserInfoBean selectUserInfo(UserInfoReq request);

}
