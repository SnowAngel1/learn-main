package com.example.polymorphic.override;

/**
 * @author：江南
 * @Description 继承+重写方法实现多态demo
 * @since 1.0.0
 */
public class Example {
    public static void test(DynamicArray dynamicArray){
        dynamicArray.add(5);
        dynamicArray.add(1);
        dynamicArray.add(3);
        for (int i = 0; i < dynamicArray.size; ++i) {
            System.out.println(dynamicArray.get(i));
        }
    }

    public static void main(String[] args) {
        DynamicArray dynamicArray = new SortedDynamicArray();
        test(dynamicArray);//输出结果 1、2、5
    }
}
