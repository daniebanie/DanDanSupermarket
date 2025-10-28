package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MoneyCounter {

    Text text = new  Text();
    Rectangle rectangle;
    StackPane stack = new StackPane();
    public MoneyCounter(BorderPane borderPane, Double  x, Double y) {
        rectangle = new Rectangle(200,50);
        rectangle.setFill(Color.LIGHTGREEN);
        rectangle.setStroke(Color.BLACK);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        stack.getChildren().addAll(rectangle, text);
        stack.setLayoutX(x);
        stack.setLayoutY(y);
        borderPane.getChildren().add(stack);
    }

    public void update() {
        this.text.setText("Money: " + String.format("%.2f", Store.storeMoney));
    }
}
