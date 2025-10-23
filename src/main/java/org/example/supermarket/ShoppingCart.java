package org.example.supermarket;

import java.util.ArrayList;

public class ShoppingCart {
    public ArrayList<Product> items  = new ArrayList<Product>();
    public double totalPrice = 0;

    public ShoppingCart()
    {
    }

    public void addItem(Product item)
    {
        this.items.add(item);
    }

    public double calculateTotal()
    {
        totalPrice = 0;
        for (Product item : items)
        {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
