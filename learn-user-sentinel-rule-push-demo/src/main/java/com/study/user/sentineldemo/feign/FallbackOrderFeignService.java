package com.study.user.sentineldemo.feign;

import com.common.utils.R;
import com.study.user.sentineldemo.domain.request.UserInfoReq;
import org.springframework.stereotype.Component;

/**
 * @author Fox
 */
@Component   //必须交给spring 管理
public class FallbackOrderFeignService implements UserFeignService {
    @Override
    public R findOrderByUserId(UserInfoReq request) {
        return R.error(-1,"=======服务降级了========");
    }
}
