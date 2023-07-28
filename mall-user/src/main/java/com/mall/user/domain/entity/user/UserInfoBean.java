package com.mall.user.domain.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@Data
public class UserInfoBean implements Serializable {
    private static final long serialVersionUID = 5470833338699840753L;

    private Integer id;

    private String userName;

    private Integer age;
}
