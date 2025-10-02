package org.example.supermarket;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Person{
    private String name;

    private Rectangle rectangle;
    public Person(String name, BorderPane borderPane, Color color) {

        this.name = name;
        rectangle = new Rectangle(200,200,50,50);
        rectangle.fillProperty().set(color);
        borderPane.getChildren().add(rectangle);
    }


    public void moveTo(int x, int y)
    {
        double time = distanceBetweenPoints(rectangle.getX(), rectangle.getY(), x, y) / 40;
        KeyValue keyValueX = new KeyValue(rectangle.xProperty(), x);
        KeyValue keyValueY = new KeyValue(rectangle.yProperty(), y);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), keyValueX, keyValueY);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    public double distanceBetweenPoints(double x1, double y1, double x2, double y2) {

        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);

        return Math.hypot(ac, cb);
    }
}