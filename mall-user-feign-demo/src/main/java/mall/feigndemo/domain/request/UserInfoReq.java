package mall.feigndemo.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@Data
public class UserInfoReq implements Serializable {
    private static final long serialVersionUID = 4352915112632989596L;

    private Integer id;
}
