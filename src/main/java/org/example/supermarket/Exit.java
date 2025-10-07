package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Exit extends GameObject {
    public Exit(BorderPane borderPane, Double x, Double y) {
        super(borderPane, "Exit", Color.RED, x, y, 200, 100);
    }

}
