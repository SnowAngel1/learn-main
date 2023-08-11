package com.learn.skywalking.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenYP
 */
@Data
public class UserDO implements Serializable {

    private static final long serialVersionUID = 252611617811308875L;

    private String userName;

    private Integer age;


}
