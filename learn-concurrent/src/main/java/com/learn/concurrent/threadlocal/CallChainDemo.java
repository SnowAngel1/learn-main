package com.learn.concurrent.threadlocal;

import java.util.UUID;

/**
 * @author ChenYP
 * 使用ThreadLocal实现接口的调用链路追踪，传递tid
 */

public class CallChainDemo {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();


    public static void main(String[] args) {
        // 设置tid
        THREAD_LOCAL.set(UUID.randomUUID().toString());
        try {
            // 调用服务A
            ServiceA serviceA = new ServiceA();
            serviceA.doSomething();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            THREAD_LOCAL.remove();
        }


    }

    static class ServiceA {
        private void doSomething() {
            String id = THREAD_LOCAL.get();
            System.out.println("ServiceA" + id);
            ServiceB serviceB = new ServiceB();
            serviceB.doSomething();

        }
    }


    static class ServiceB {
        private void doSomething() {
            String id = THREAD_LOCAL.get();
            System.out.println("ServiceB" + id);
            ServiceC serviceC = new ServiceC();
            serviceC.doSomething();

        }

    }


    static class ServiceC {
        private void doSomething() {
            String id = THREAD_LOCAL.get();
            System.out.println("ServiceC" + id);


        }

    }
}
