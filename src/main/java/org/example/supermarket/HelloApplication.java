package org.example.supermarket;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        List<Circle> circles = new ArrayList<>();
        List<Rectangle> rectangles = new ArrayList<>();
        List<Customer>  customers = new ArrayList<>();
        Circle circle = new Circle(100, 100, 20, Color.RED);
        Circle circle2 = new Circle(100, 100, 20, Color.BLUE);
        Circle circle3 = new Circle(100, 100, 20, Color.GREEN);
        Circle circle4 = new Circle(300, 300, 20, Color.BLACK);
//        Image img = new Image("C:/Users/danie/IdeaProjects/Supermarket/src/main/sprites/mario.png", true);
//        circle.setFill(new ImagePattern(img));
        Rectangle rectangle = new Rectangle(20, 100, Color.BLACK);
        circles.add(circle);
        circles.add(circle2);
        Customer c1 = new Customer("Test", 20, "23d");

        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().addAll(circles);
        borderPane.getChildren().add(rectangle);

        Scene scene = new Scene(borderPane, 1000, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("DanDan");
        circles.add(circle3);
        borderPane.getChildren().add(circle3);
        primaryStage.show();
        moveTo(circle, 300, 100);
        moveTo(circle2, 100, 300);
        moveTo(circle3, 300, 300);
        moveTo(circle4, 100, 100);
        moveToRect(rectangle, 300, 300);
        c1.displayInfo();
        c1.moveTo(100,300);
//        borderPane.getChildren().add(c1.circle());
    }
    public void moveTo(Circle circle, int x, int y)
    {
        double time = distanceBetweenPoints(circle.getCenterX(), circle.getCenterY(), x, y) / 40;
        KeyValue keyValueX = new KeyValue(circle.centerXProperty(), x);
        KeyValue keyValueY = new KeyValue(circle.centerYProperty(), y);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), keyValueX, keyValueY);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    public void moveToRect(Rectangle rectangle, int x, int y)
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