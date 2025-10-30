package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * stelt de uitgang van de supermarkt voor
 */
public class Entrance extends GameObject {
    /**
     * maakt een nieuwe uitgang aan
     *
     * @param borderPane het borderpane waarop de uitgang wordt geplaatst
     * @param x de x-positie
     * @param y de y-positie
     */
    public Entrance(BorderPane borderPane, Double x, Double y) {
        super(borderPane, "Exit", Color.BLUEVIOLET, x, y, 200, 100);
    }

}
