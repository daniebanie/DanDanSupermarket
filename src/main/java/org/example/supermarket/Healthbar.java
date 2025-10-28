package org.example.supermarket;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Healthbar {
    public Rectangle healthRectangle;
    public Label capacityLabel;
    public int value1;
    public int value2;

    public Healthbar(BorderPane borderPane, int value1, int value2, double x, double y) {
        this.value1 = value1;
        this.value2 = value2;

        // maak een healthbar aan
        healthRectangle = new Rectangle(x, y - 20, 100, 10);
        healthRectangle.setFill(Color.GREEN);
        healthRectangle.setStroke(Color.BLACK);

        // Create capacity label
        capacityLabel = new Label(value1 + "/" + value2);
        capacityLabel.setLayoutX(x + 40 - (capacityLabel.getWidth() / 2));
        capacityLabel.setLayoutY(y - 30);

        // voeg toe aan borderPane
        borderPane.getChildren().addAll(healthRectangle, capacityLabel);
    }

    public void updateHealthBar(int newValue1) {
        this.value1 = newValue1;
        double healthPercentage = (double) value1 / value2;
        healthRectangle.setWidth(100 * healthPercentage);
        healthRectangle.setFill(healthPercentage > 0.3 ? Color.GREEN : Color.RED);
        capacityLabel.setText(value1 + "/" + value2);
    }
}
