package org.example.supermarket;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HelloApplication extends Application {

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



        launch(args);
    }



    public void start(Stage primaryStage) {
        // Scherm maken, AFBLIJVEN!!!
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: lightblue;");
        Scene scene = new Scene(borderPane, 1920, 1009);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DanDan");

        // 1 Entrance en 1 Exit maken, kunnen door de Store class in elke andere klasse gevonden worden
        Store.entrance = new Entrance(borderPane, 1500.0, 50.0);
        Store.exit = new Exit(borderPane, 300.0, 50.0);

        // Meerdere checkouts
        Store.checkouts.add(new Checkout("C1", borderPane, 500, 400));
        Store.checkouts.add(new Checkout("C2", borderPane, 500, 600));
        Store.checkouts.add(new Checkout("C3", borderPane, 500, 800));

        Store.controller = new Controller(borderPane);
        Store.shelves.add(new Shelve("S1", borderPane, 1000, 400));
        Shelve s1 = new Shelve("Shelve1", borderPane, 1200, 400);
        Shelve s2 = new Shelve("Shelve2", borderPane, 800, 500);
        Store.customers.add(new Customer("Don", borderPane, List.of(s1)));
        Store.customers.add(new Customer("Kian",  borderPane, List.of(s2,s2,s1)));
        Store.customers.add(new Customer("Bas", borderPane, List.of(s1,s2,s1)));

        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (lastUpdate == 0) lastUpdate = now;
                double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                lastUpdate = now;
                for (Customer c : Store.customers) {
                    c.update(deltaTime);
                }


            }
        };
        gameLoop.start();

        primaryStage.show();
    }
}