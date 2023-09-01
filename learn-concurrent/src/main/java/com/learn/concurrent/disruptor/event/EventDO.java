package com.learn.concurrent.disruptor.event;

import lombok.Data;

/**
 * @author ChenYP
 * 时间消息载体
 */
@Data
public class EventDO {

    private String name;

    private String value;
}
