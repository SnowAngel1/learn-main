package com.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author ChenYP
 * @date 2023/7/19 8:55
 * @Describe
 */
@TableName("t_dict")
public class Dict implements Serializable {
    private static final long serialVersionUID = 4703694845907160403L;

    private Long diceId;

    private String ustatus;

    private String uvalue;

    public Long getDiceId() {
        return diceId;
    }

    public void setDiceId(Long diceId) {
        this.diceId = diceId;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getUvalue() {
        return uvalue;
    }

    public void setUvalue(String uvalue) {
        this.uvalue = uvalue;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "diceId=" + diceId +
                ", ustatus='" + ustatus + '\'' +
                ", uvalue='" + uvalue + '\'' +
                '}';
    }
}
