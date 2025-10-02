package org.example.supermarket;

import javafx.scene.paint.Color;

public class Customer extends Person {
    private String customerId;

    public Customer(String name, String customerId, javafx.scene.layout.BorderPane borderPane) {
        super(name, borderPane, Color.GREEN);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void displayInfo() {
        System.out.println("Customer ID: " + customerId);
    }
}