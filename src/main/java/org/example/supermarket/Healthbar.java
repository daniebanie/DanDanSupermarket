package org.example.supermarket;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Healthbar {
    public Rectangle healthRectangle;
    public Label capacityLabel;
    public int value1;
    public int value2;
    public double maxWidth;

    public Healthbar(StackPane stack, int value1, int value2, double x, double y, double maxWidth) {
        this.value1 = value1;
        this.value2 = value2;
        this.maxWidth = maxWidth;

        // maak een healthbar aan
        healthRectangle = new Rectangle(x, y, maxWidth, 10);
        healthRectangle.setFill(Color.GREEN);
        healthRectangle.setStroke(Color.BLACK);

        // Create capacity label
        capacityLabel = new Label(value1 + "/" + value2);
        capacityLabel.setLayoutX(x);
        capacityLabel.setLayoutY(y);


        // voeg toe aan borderPane

        stack.getChildren().addAll(healthRectangle, capacityLabel);

    }

    public void updateHealthBar(int newValue1, ObjectType objectType) {

        this.value1 = newValue1;
        double healthPercentage = (double) value1 / value2;
        healthRectangle.setWidth(maxWidth * healthPercentage);
        healthRectangle.setFill(healthPercentage > 0.3 ? objectType.getColorHigh() : objectType.getColorLow());
        capacityLabel.setText(value1 + "/" + value2);
    }

    public enum ObjectType {
        SHELF(Color.GREEN, Color.RED),
        RESTOCKER(Color.SKYBLUE, Color.BLUE),
        CUSTOMER(Color.GOLD, Color.YELLOW);

        private final Color colorHigh;
        private final Color colorLow;

        ObjectType(Color colorHigh, Color colorLow) {
            this.colorHigh = colorHigh;
            this.colorLow = colorLow;
        }

        public Color getColorHigh() {
            return colorHigh;
        }
        public Color getColorLow() {
            return colorLow;
        }
    }
}
