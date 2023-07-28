package com.study.user.sentineldemo.feign;

import com.common.utils.R;
import com.study.user.sentineldemo.domain.request.UserInfoReq;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Fox
 */
@Component
public class FallbackOrderFeignServiceFactory implements FallbackFactory<UserFeignService> {
    @Override
    public UserFeignService create(Throwable throwable) {

        return new UserFeignService() {
            @Override
            public R findOrderByUserId(UserInfoReq request) {
                return R.error(-1,"=======服务降级了========");
            }
        };
    }
}
