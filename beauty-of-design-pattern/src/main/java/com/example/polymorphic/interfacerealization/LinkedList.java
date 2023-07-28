package com.example.polymorphic.interfacerealization;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
public class LinkedList implements Iterator{

    private LinkedListNode head;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public String remove() {
        return null;
    }

    class LinkedListNode{

    }
}
