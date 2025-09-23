package org.example.supermarket;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Person {
    private String name;
    private int age;
    private final Circle circle = createPerson();

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Circle createPerson() {
        Circle circle = new Circle(100, 100, 20, Color.RED);
        return circle;
    }

    public void moveTo(int x, int y) {
        double time = distanceBetweenPoints(circle.getCenterX(), circle.getCenterY(), x, y) / 40;
        KeyValue keyValueX = new KeyValue(circle.centerXProperty(), x);
        KeyValue keyValueY = new KeyValue(circle.centerYProperty(), y);

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