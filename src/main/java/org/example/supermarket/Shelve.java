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
    private Rectangle healthBar;
    private Label capacityLabel;

    public Shelve(String productName, BorderPane borderPane, double x, double y, int maxCapacity) {
        super(borderPane, productName, Color.BROWN, x, y, 100, 70);
        this.product = product;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = maxCapacity;

        // maak een healthbar aan
        healthBar = new Rectangle(x, y - 20, 100, 10);
        healthBar.setFill(Color.GREEN);
        healthBar.setStroke(Color.BLACK);

        // Create capacity label
        capacityLabel = new Label(currentCapacity + "/" + maxCapacity);
        capacityLabel.setLayoutX(x + 40 - (capacityLabel.getWidth() / 2));
        capacityLabel.setLayoutY(y - 30);

        // voeg toe aan borderPane
        borderPane.getChildren().addAll(healthBar, capacityLabel);
    }


    public void removeProduct() {
        if (currentCapacity > 0) {
            currentCapacity--;
            updateHealthBar();
        }
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void removeDeadShelves(Shelve shelve) {
    }

    private void updateHealthBar() {
        double healthPercentage = (double) currentCapacity / maxCapacity;
        healthBar.setWidth(100 * healthPercentage);
        healthBar.setFill(healthPercentage > 0.3 ? Color.GREEN : Color.RED);
        capacityLabel.setText(currentCapacity + "/" + maxCapacity);
    }
}