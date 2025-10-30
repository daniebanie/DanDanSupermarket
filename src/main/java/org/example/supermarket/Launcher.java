package org.example.supermarket;

import javafx.application.Application;

/**
 * startpunt van de applicatie.
 * start de javafx-applicatie van de supermarkt.
 */
public class Launcher {

    /**
     * main-methode die de javafx-applicatie opstart
     *
     * @param args eventuele commandoregelargumenten
     */
    public static void main(String[] args) {
        Application.launch(Supermarket.class, args);
    }
}
