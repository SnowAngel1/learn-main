package com.learn.concurrent.container.collection;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ChenYP
 * ArrayList遍历时，其他线程添加元素会报错
 * CopyOnWriteArrayList不能保证数据的实时更新，使用空间换时间数组太大容易造成full GC
 */

public class ArrayListDemo {
    public static void main(String[] args) throws InterruptedException {

        // List<String> list = new ArrayList<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");

        new Thread(()-> list.add("4")).start();
        Thread.sleep(100);

        for (String s : list) {
            System.out.println(s);
        }



    }

}
