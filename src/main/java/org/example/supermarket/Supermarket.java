package org.example.supermarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * de hoofklasse van de supermarktapplicatie.
 * start grafische weergave van de supermarkt.
 */
public class Supermarket extends Application {

    /**
     * startpunt van de applicatie.
     * toont alle producten, producten per categorie en een willekeurig product in de console.
     * start vervolgens de JavaFX GUI.
     *
     * @param args commandline-argumenten (niet gebruikt)
     */
    public static void main(String[] args) {

        ProductCatalog catalog = new ProductCatalog();

        System.out.println("Alle producten:");
        for (Product p : catalog.getAllProducts()) {
            System.out.println(p.getName() + " - €" + p.getPrice() + " (" + p.getCategory() + ")");
        }

        System.out.println("\nProducten in de categorie 'Snacks':");
        for (Product p : catalog.getProductsByCategory("Snacks")) {
            System.out.println(p.getName() + " - €" + p.getPrice());
        }

        System.out.println("\nRandom product:");
        Product randomProduct = catalog.getRandomProduct();
        System.out.println(randomProduct.getName() + " - €" + randomProduct.getPrice());

        // Start de grafische weergave
        launch(args);
    }

    /**
     * start de JavaFX GUI van de supermarkt.
     * maakt het hoofdscherm, initialiseert de winkel en start de game loop.
     *
     * @param primaryStage het hoofdvenster van de applicatie
     */
    @Override
    public void start(Stage primaryStage) {

        // Hoofdscherm maken
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: lightblue;");
        Scene scene = new Scene(borderPane, 1920, 1009);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DanDan");

        // Winkel initialiseren
        Store.create(borderPane);

        // Game loop om de supermarkt te updaten
        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) lastUpdate = now;
                double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                lastUpdate = now;

                // Winkelcontroller updaten
                Store.controller.update(borderPane, deltaTime);
            }
        };
        gameLoop.start();

        primaryStage.show();
    }
}
