package org.example.supermarket;

import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

public class Checkout extends GameObject{
    public Checkout(String itemName, BorderPane borderPane, double x, double y)
    {
        super(borderPane, itemName, Color.GRAY, x, y, 100, 70);
    }
}
