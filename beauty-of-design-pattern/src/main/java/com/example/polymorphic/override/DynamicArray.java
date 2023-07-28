package com.example.polymorphic.override;

/**
 * @author：江南
 * @Description 继承+重写方法实现多态demo
 * @since 1.0.0
 */
public class DynamicArray {
    private static final int DEFAULT_CAPACITY = 10;
    protected int size = 0;
    protected int capacity = DEFAULT_CAPACITY;
    protected Integer[] elements = new Integer[DEFAULT_CAPACITY];

    public int size() {
        return this.size;
    }

    public  Integer get(int index){
        return elements[index];
    }

    public void add(Integer e){
        //扩容
        ensureCapacity();
        elements[size++] = e;
    }

    protected void ensureCapacity() {
        //如果数组满了，就扩容、代码省略
    }


}
