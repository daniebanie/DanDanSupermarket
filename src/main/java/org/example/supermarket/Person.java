package org.example.supermarket;

import com.almasb.fxgl.entity.Entity;
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


abstract class Person extends GameObject{
    public Person(String name, BorderPane borderPane, Color color,  double x, double y, double width, double height) {
        super(borderPane, name, color, x, y, width, height);
    }
    protected double targetX;
    protected double targetY;
    protected double walkingSpeed = 100.0;

    public void moveTo(double x, double y)
    {
        this.targetX = x;
        this.targetY = y;
        double time = distanceBetweenPoints(rectangle.getX(), rectangle.getY(), x, y) / this.walkingSpeed;
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