package com.learn.concurrent.jmm;

/**
 * @author ChenYP
 * 伪共享的案例：
 * 多个互相不干扰的变量共享一个缓存行到处伪共享
 * 伪共享的出现，在操作系统中缓存行为64字节，比如定义两个long类型的变量x、y是不会超过64字节的，所以两个变量会读在一个缓存行中，这时有两个线程分别对两个变量做自增操作，
 * 当其中一个变量被修改了，因为在一个缓存行中，另一个线程中的变量会被制为失效，再次中主内存中获取新的值，其实改的并不是一个变量，这就造成了以为操作的是一个变量，其实不是。
 */

public class FalseShardingTest {


    public static void main(String[] args) throws InterruptedException {
        pointerTest(new Pointer());
    }

    private static void pointerTest(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000000; i++) {
                    pointer.y++;
                }
            }
        });
        thread.start();
        thread1.start();
        thread.join();

        thread1.join();
        // 思考：x,y是线程安全的吗？
        System.out.println(pointer.x + "," + pointer.y);
        System.out.println(System.currentTimeMillis() - start);

    }
    static class A implements Runnable{
        @Override
        public void run() {
            System.out.println("zzzz");
        }
    }



}
class Pointer{
    // 解决伪共享方案二：@Contended + jvm参数： -XX:-RestrictContended jdk8支持
    //@Contended
    volatile long x;

    // 解决伪共享方案一
    long p1,p2,p3,p4,p5,p6,p7;

    volatile long y;
}

