package com.example.polymorphic.interfacerealization;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
public class Array implements Iterator{

    private String[] data;
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
}
