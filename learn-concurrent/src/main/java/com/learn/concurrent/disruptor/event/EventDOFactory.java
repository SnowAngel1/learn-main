package com.learn.concurrent.disruptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * @author ChenYP
 * 事件工厂
 */

public class EventDOFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new EventDO();
    }
}
