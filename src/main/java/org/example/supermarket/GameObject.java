package org.example.supermarket;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class GameObject {
    String objectName;
    StackPane stack = new StackPane();
    public Random rand = new Random();
    public Healthbar healthbar;

    protected Rectangle rectangle;
    public GameObject(BorderPane borderPane, String objectName, Color color, double x, double y, double width, double height) {
        this.objectName = objectName;
        rectangle = new Rectangle(x,y,width,height);
        rectangle.fillProperty().set(color);
        rectangle.setStroke(Color.BLACK);

        stack.getChildren().addAll(rectangle);

        stack.setLayoutX(x);
        stack.setLayoutY(y);
        stack.setAlignment(Pos.TOP_LEFT);
        borderPane.getChildren().add(stack);
    }

    double getX() {
        return stack.getLayoutX();
    }
    double getY() {
        return stack.getLayoutY();
    }

    public void setName(String name) {
        this.objectName = name;
    }

    public String getName()
    {
        return this.objectName;
    }
}
