package com.learn.concurrent;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ChenYP
 * 查看对象大小
 * 关闭指针压缩 (-XX:-UseCompressedOops)
 */

public class ObjectTest {
    public static void main(String[] args) {
        Object object  = new Test();
        // 查看对象内部信息
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
}

class Test{
    private long p;
}
