package com.study.user.sentineldemo.feign;

import com.common.utils.R;
import com.study.user.sentineldemo.domain.request.UserInfoReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@FeignClient(value = "mall-user",path = "/user")
public interface UserFeignService {

    @RequestMapping("/get/user")
    R findOrderByUserId(UserInfoReq request);
}
