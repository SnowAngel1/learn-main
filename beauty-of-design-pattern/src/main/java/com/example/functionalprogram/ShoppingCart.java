package com.example.functionalprogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
public class ShoppingCart {
    private int itemCount;

    private double totalPrice;

    private List<ShoppingCart> items = new ArrayList<>();

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ShoppingCart> getItems() {
        return Collections.unmodifiableList(this.items);
    }



    public void addItem(ShoppingCart item){
        items.add(item);
        itemCount++;
        totalPrice += item.getTotalPrice();
    }
}
