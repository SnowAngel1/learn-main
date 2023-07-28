package com.jvm;

/**
 * @author ChenYP
 * @date 2023/7/3 15:51
 * @describe 类加载的过程（加载————>验证————>准备————>解析————>初始化）
 */
public class Math {
    public static int INIT_DATA = 666;

    public static User user = new User();

    public int compute(){
        //一个方法对应一块栈帧内存区域
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }

}
