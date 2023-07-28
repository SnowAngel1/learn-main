package com.jvm;

/**
 * @author ChenYP
 * @date 2023/7/5 16:40
 * @describe 模拟栈溢出
 */
public class StackOverflowTest {
    /**
     * JVM 设置
     * -Xss128k
     * -Xss1M
     */

    private static int count = 0;

    static void redo(){
        count++;
        redo();
    }
    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(count);
        }

    }
}
