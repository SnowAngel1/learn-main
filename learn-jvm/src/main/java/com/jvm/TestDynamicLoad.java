package com.jvm;

/**
 * @author 江南
 * @date 2023/5/18
 * 类的懒加载
 */
public class TestDynamicLoad {
    static {
        System.out.println("***********load TestDynamicLoad ************");
    }

    public static void main(String[] args){
        new A();
        System.out.println("*****************load test****************");

        B b = null;

    }
}

class A{
    static {
        System.out.println("**************load A ********************");
    }
    public A(){
        System.out.println("****************initial A*******************");
    }
}

class B{
    static {
        System.out.println("**************load B ********************");
    }
    public B(){
        System.out.println("****************initial B*******************");
    }
}
