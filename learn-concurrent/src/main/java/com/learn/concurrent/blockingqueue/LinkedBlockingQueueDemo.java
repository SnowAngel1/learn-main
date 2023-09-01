package com.learn.concurrent.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ChenYP
 * LinkedBlockingQueue
 * 缺点：
 *      使用链表，会占用内存空间很可能会发生OOM
 */

public class LinkedBlockingQueueDemo {

    private static final LinkedBlockingQueue LINKED_BLOCKING_QUEUE = new LinkedBlockingQueue();
}
