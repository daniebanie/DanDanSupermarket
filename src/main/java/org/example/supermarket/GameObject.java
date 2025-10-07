package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class GameObject {
    private String objectName;
    public Random rand = new Random();

    protected Rectangle rectangle;
    public GameObject(BorderPane borderPane, String objectName, Color color, double x, double y, double width, double height) {

        this.objectName = objectName;
        rectangle = new Rectangle(x,y,width,height);
        rectangle.fillProperty().set(color);
        borderPane.getChildren().add(rectangle);
    }

    double getX() {
        return rectangle.getX();
    }
    double getY() {
        return rectangle.getY();
    }
}
