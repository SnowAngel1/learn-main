package com.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ChenYP
 * @date 2023/7/5 18:00
 * @describe 计算对象大小
 */
public class JolSamole {
    public static void main(String[] args) {

        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        System.out.println();
        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());

        System.out.println();
        ClassLayout layout2 = ClassLayout.parseInstance(new A());
        System.out.println(layout2.toPrintable());

    }

    /**
     * -XX:+UserCompressedOops   默认开启的压缩所有指针
     * -XX:+UserCompressedClassPointers   默认开启的只压缩对像头中的类型指针（Klass Pointer）
     * Oops : Ordinary Object Pointers
     */
    public static class A {
        int id;

        byte b;
        Object o;
    }
}

