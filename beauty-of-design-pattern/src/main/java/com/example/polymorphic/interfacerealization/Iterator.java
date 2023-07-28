package com.example.polymorphic.interfacerealization;

/**
 * @author：江南
 * @Description 利用接口方式实现多态
 * @since 1.0.0
 */
public interface Iterator {
    /**
     * 是否存在下一个元素
     * @return
     */
    boolean hasNext();

    /**
     * 下一个元素
     * @return
     */
    String next();

    /**
     * 移出元素
     * @return
     */
    String remove();
}
