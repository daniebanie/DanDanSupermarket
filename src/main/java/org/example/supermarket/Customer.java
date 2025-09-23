package org.example.supermarket;

public class Customer extends Person {
    private String customerId;

    public Customer(String name, int age, String customerId) {
        super(name, age);
        this.customerId = customerId;
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