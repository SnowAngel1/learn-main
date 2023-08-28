package com.learn.concurrent;

import java.lang.ref.WeakReference;

/**
 * @author ChenYP
 * 弱引用与强引用
 */

public class TestWeakRed {

    static class User {
        private String userName;

        private String id;

        public User() {
        }

        public User(String userName, String id) {
            this.userName = userName;
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        // 强引用
        User user = new User("ChenYP", "1");
        // User user1 = user;
        // user = null;
        // System.gc();
        // System.out.println(user1);

        // 弱引用
        WeakReference<User> userWeakReference = new WeakReference<>(user);
        user = null;
        System.out.println(userWeakReference.get());
        System.gc();
        System.out.println(userWeakReference.get());


    }



}
