package org.example.supermarket;

public class DrinkProduct extends Product {

    // Constructor
    public DrinkProduct(String name, double price, String category) {
        super(name, price, category);
    }

    @Override
    public String toString() {
        return "DrinkProduct{name='" + getName() + "', price=" + getPrice() +
                ", category='" + getCategory() + "'}";
    }
}

