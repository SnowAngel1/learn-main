package com.jvm;

/**
 * @author 江南
 * @date 2023/5/18
 */
public class User1 {

    private Integer id;

    private String name;

    static {
        System.out.println("*******************load**********************");
    }

    public User1() {
    }

    public User1(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sout(){
        System.out.println("================另外一个USer1的版本：自己的类加载器加载类调用的方法");
    }
}
