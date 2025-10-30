package org.example.supermarket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * eenvoudige controllerklasse voor de javafx-demo.
 * beheert de interactie met het label in de gebruikersinterface.
 */
public class HelloController {

    /** label waarop tekst wordt weergegeven */
    @FXML
    private Label welcomeText;

    /**
     * wordt uitgevoerd wanneer op de knop wordt geklikt.
     * past de tekst van het label aan.
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("welcome to javafx application!");
    }
}
