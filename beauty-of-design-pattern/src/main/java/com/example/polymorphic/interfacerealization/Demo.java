package com.example.polymorphic.interfacerealization;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
public class Demo {
    private static void print(Iterator iterator){
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        Iterator arrayIterator = new Array();
        print(arrayIterator);
        Iterator linkedListIterator = new LinkedList();
        print(linkedListIterator);
    }
}
