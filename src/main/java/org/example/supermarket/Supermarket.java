package org.example.supermarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Supermarket extends Application {

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
        Store.create(borderPane);





        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (lastUpdate == 0) lastUpdate = now;
                double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                lastUpdate = now;

                List<Customer> toKill = new ArrayList<>();

                for (Customer c : Store.customers) {
                    c.update(deltaTime, borderPane);
                    if (c.isDone()) {
                        toKill.add(c);
                    }
                }

                for (Customer c : toKill) {
                    borderPane.getChildren().remove(c.rectangle);
                    Store.customers.remove(c);
                    Store.customerCounter.update();
                }

                if (Store.restockers.isEmpty())
                {
                    Store.restockers.add(new Restocker("Bart", borderPane));
                    Store.restockers.add(new Restocker("Bart", borderPane));
                    Store.restockers.add(new Restocker("Bart", borderPane));
                }

                Store.controller.spawnCustomers(borderPane);
                Store.controller.checkRestockableShelves();

                for (Restocker r : Store.restockers) {
                    r.update();
                }



            }
        };
        gameLoop.start();

        primaryStage.show();
    }
}