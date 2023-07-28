package com.example.polymorphic.override;

/**
 * @author：江南
 * @Description 继承+重写方法实现多态demo
 * @since 1.0.0
 */
public class SortedDynamicArray extends DynamicArray{
    @Override
    public void add(Integer e) {
        ensureCapacity();
        int i ;
        for (i = size() - 1; i >= 0; --i) {//保证数组中的数组有顺序
            if(elements[i] > e){
                elements[i + 1] = elements[i];
            }else {
                break;
            }
        }
        elements[i + 1] = e;
        ++size;
    }
}
