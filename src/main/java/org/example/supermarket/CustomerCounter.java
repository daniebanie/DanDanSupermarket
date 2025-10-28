package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class CustomerCounter {

    Text text = new  Text();
    Rectangle rectangle;
    StackPane stack = new StackPane();
    public CustomerCounter(BorderPane borderPane, Double  x, Double y) {
        rectangle = new Rectangle(175,40);
        rectangle.setFill(Color.LIGHTGOLDENRODYELLOW);
        rectangle.setStroke(Color.BLACK);

        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        stack.getChildren().addAll(rectangle, text);
        stack.setLayoutX(x);
        stack.setLayoutY(y);
        borderPane.getChildren().add(stack);
    }

    public void update() {
        this.text.setText("Customers: " + Store.customers.size() + " / " + Store.controller.getMaxCustomers());
    }
}
