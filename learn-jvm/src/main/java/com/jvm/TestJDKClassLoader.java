package com.jvm;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;

/**
 * @author 江南
 * @date 2023/5/18
 * 类加载器
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        //String类是C++实现的，所以会打印null
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader());
        System.out.println(TestJDKClassLoader.class.getClassLoader());


        System.out.println("******************************");
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassLoader.getParent();

        System.out.println("the bootstrapLoader：" + bootstrapLoader);
        System.out.println("the extClassLoader：" + extClassLoader);
        System.out.println("the appClassLoader：" + appClassLoader);
        System.out.println("*************************");

        System.out.println("bootstrapLoader加载以下文件");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }


        System.out.println("*************************");

        System.out.println("extClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("**********************");
        System.out.println("appClassLoader加载以下文件");
        System.out.println(System.getProperty("java.class.path"));

    }
}
