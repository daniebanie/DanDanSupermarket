package org.example.supermarket;

import javafx.scene.paint.Color;

public class Shelve extends GameObject{
    public Shelve(String itemName, javafx.scene.layout.BorderPane borderPane, double x, double y)
    {
        super(borderPane, itemName, Color.BROWN, x, y, 100, 70);
    }
}
