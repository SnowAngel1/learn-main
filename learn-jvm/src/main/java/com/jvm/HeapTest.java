package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYP
 * @date 2023/7/5 15:25
 * @describe 模拟OOM
 */
public class HeapTest {

    byte[] bytes = new byte[102 * 1024]; //100KB

    public static void main(String[] args) throws InterruptedException {

        List<HeapTest> heapTestList = new ArrayList<>();

        while (true){
            heapTestList.add(new HeapTest());
            Thread.sleep(10);
        }

    }


}
