package org.example.supermarket;

import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

public class Shelve extends GameObject{
    public Product product;
    public Shelve(String itemName, BorderPane borderPane, double x, double y)
    {
        super(borderPane, itemName, Color.BROWN, x, y, 100, 70);
    }

    public String GetProductName()
    {
        return this.objectName;
    }

}

