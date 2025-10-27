package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Storage extends GameObject {
    public Storage(BorderPane borderPane, Double x, Double y) {
        super(borderPane, "Storage", Color.YELLOW, x, y, 200, 100);
    }

}
