package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Entrance extends GameObject {
    public Entrance(BorderPane borderPane, Double x, Double y) {
        super(borderPane, "Exit", Color.BLUEVIOLET, x, y, 200, 100);
    }

}
