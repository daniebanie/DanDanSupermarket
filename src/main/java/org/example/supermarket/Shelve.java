package org.example.supermarket;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.BorderPane;

public class Shelve extends GameObject {
    private Product product;
    private int maxCapacity;
    private int currentCapacity;

    public Shelve(String productName, BorderPane borderPane, double x, double y, int maxCapacity) {
        super(borderPane, productName, Color.BROWN, x, y, 100, 70);
        this.product = product;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = maxCapacity;

        this.healthbar = new Healthbar(stack, currentCapacity, maxCapacity, x, y , 100);

    }


    public void removeProduct() {
        if (currentCapacity > 0) {
            currentCapacity--;
            this.healthbar.updateHealthBar(currentCapacity, Healthbar.ObjectType.SHELF);
        }
    }
    public void addProduct() {
        if (currentCapacity < maxCapacity) {
            currentCapacity++;
            this.healthbar.updateHealthBar(currentCapacity, Healthbar.ObjectType.SHELF);
        }
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Boolean needsRestock() {
        if ((double) currentCapacity / maxCapacity <= 0.8) {
            return true;
        }
        return false;
    }
    public Boolean isFull() {
        if (currentCapacity == maxCapacity) {
            return true;
        }
        return false;
    }


}