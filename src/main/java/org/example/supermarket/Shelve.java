package org.example.supermarket;

import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

public class Shelve extends GameObject{
    public Shelve(String itemName, BorderPane borderPane, double x, double y)
    {
        super(borderPane, itemName, Color.BROWN, x, y, 100, 70);
    }
}
