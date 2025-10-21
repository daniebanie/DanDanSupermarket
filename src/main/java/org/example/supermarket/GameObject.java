package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;
import java.util.Random;

public class GameObject {
    String objectName;
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

    public void setName(String name) {
        this.objectName = name;
    }
}
