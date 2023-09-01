package com.learn.concurrent.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ChenYP
 * 逃逸分析：如果对象没有逃逸出方法，则在栈上分配对象
 * 开启逃逸分析：-XX:+DoEscapeAnalysis
 * 关闭逃逸分析：-XX:-DoEscapeAnalysis
 */

public class EscapeTest {
    public static void main(String[] args) {
        long start  = System.currentTimeMillis();

        for (int i = 0; i < 500000; i++) {
            test();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" +(end - start));
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * JIT 编译时会对代码进行逃逸分析
     * 并不是所有对象存放在堆区，有的一部分在线程栈空间分配
     * 这里的Point没有逃逸
     * @return
     */
    private static String test(){
        Point point = new Point();
        return point.toString();
    }





    /**
     * 标量替换
     */
    private static void test2(){
        Point point = new Point(1,2);
        System.out.println("point.x=" + point.getX() + "point.y=" + point.getY());
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Point{
    private int x;

    private int y;
}

