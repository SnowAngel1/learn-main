package com;

import java.util.Random;
import java.util.Scanner;

/**
 * @author 江南
 * @date 2023/4/24
 */
public class GameMain {
    public static void main(String[] args) throws InterruptedException {
        // 生成 Random 对象
        Random random = new Random();
        int number = random.nextInt(100);
        Scanner scanner = new Scanner(System.in);
        while (true){
            int nextInt = scanner.nextInt();
            if (nextInt == number){
                System.out.println("哈哈哈，你猜对啦，受罚");
                break;
            }
            if (nextInt > number){
                System.out.println("猜大啦。。。。");
                continue;
            }
            if (nextInt < number){
                System.out.println("猜小啦。。。。");
                continue;
            }
        }


    }
}
