package mall.feigndemo.controller;

import com.common.utils.R;
import mall.feigndemo.domain.request.UserInfoReq;
import mall.feigndemo.feign.UserFeignService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFeignService userFeignService;

    @RequestMapping(value = "/findOrderByUserId")
    public R findOrderByUserId(@RequestBody UserInfoReq request) {
        //feign调用
        R result = userFeignService.findOrderByUserId(request);
        return result;
    }
}
