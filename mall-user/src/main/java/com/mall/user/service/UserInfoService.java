package com.mall.user.service;

import com.mall.user.domain.request.user.UserInfoReq;
import com.mall.user.domain.response.user.UserInfoResp;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
public interface UserInfoService {

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    UserInfoResp queryUserInfo(UserInfoReq request);
}
