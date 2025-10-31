package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * een teller voor het bijhouden van het geld in de supermarkt.
 * toont het huidige bedrag visueel op het scherm.
 */
public class MoneyCounter {

    /** tekstobject dat het geldbedrag weergeeft */
    Text text = new Text();

    /** achtergrond van de teller */
    Rectangle rectangle;

    /** stapelpaneel waarin de teller wordt weergegeven */
    StackPane stack = new StackPane();

    /**
     * maakt een nieuwe geldteller aan en plaatst deze op het scherm
     *
     * @param borderPane het pane waarin de teller weergegeven wordt
     * @param x x-positie van de teller
     * @param y y-positie van de teller
     */
    public MoneyCounter(BorderPane borderPane, Double x, Double y) {
        rectangle = new Rectangle(200, 50);
        rectangle.setFill(Color.LIGHTGREEN);
        rectangle.setStroke(Color.BLACK);
        text.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        stack.getChildren().addAll(rectangle, text);
        stack.setLayoutX(x);
        stack.setLayoutY(y);
        borderPane.getChildren().add(stack);
    }

    /** werkt de weergegeven waarde bij op basis van het huidige geld in de winkel */
    public void update() {
        this.text.setText("money: " + String.format("%.2f", Store.storeMoney));
    }
}
