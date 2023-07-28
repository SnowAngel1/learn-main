package com.jvm;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author ChenYP
 * @date 2023/7/4 9:27
 * @describe 自定义类加载器
 */
public class MyLoadClassTest extends ClassLoader {

    private String classPath;

    public MyLoadClassTest(String classPath) {
        this.classPath = classPath;
    }

    public static void main(String[] args) throws Exception {
        MyLoadClassTest myLoadClass = new MyLoadClassTest("D:/test");
        Class<?> loadClass = myLoadClass.loadClass("com.jvm.User1",false);
        Object obj = loadClass.newInstance();
        Method method = loadClass.getDeclaredMethod("sout", null);
        method.invoke(obj,null);
        System.out.println(loadClass.getClassLoader().getClass().getName());


        MyLoadClassTest myLoadClass1 = new MyLoadClassTest("D:/test1");
        Class<?> loadClass1 = myLoadClass1.loadClass("com.jvm.User1",false);
        Object obj1 = loadClass1.newInstance();
        Method method1 = loadClass1.getDeclaredMethod("sout", null);
        method1.invoke(obj1,null);
        System.out.println(loadClass1.getClassLoader().getClass().getName());
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            long t0 = System.nanoTime();

            if (c == null && !name.startsWith("java")) {
                long t1 = System.nanoTime();
                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }else {
                c = super.loadClass(name,false);
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    private byte[] loadByte(String name) throws IOException {
        name = name.replaceAll("\\.", "/");
        FileInputStream fileInputStream = new FileInputStream(classPath + "/" + name + ".class");
        int len = fileInputStream.available();
        byte[] data = new byte[len];
        fileInputStream.read(data);
        fileInputStream.close();
        return data;
    }


}
