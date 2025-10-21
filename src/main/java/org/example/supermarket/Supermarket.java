package org.example.supermarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
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
        Store.entrance = new Entrance(borderPane, 1500.0, 50.0);
        Store.exit = new Exit(borderPane, 300.0, 50.0);
        Controller controller = new Controller(borderPane);

        // Meerdere checkouts
        Store.checkouts.add(new Checkout("C1", borderPane, 500, 400));
        Store.checkouts.add(new Checkout("C2", borderPane, 500, 600));
        Store.checkouts.add(new Checkout("C3", borderPane, 500, 800));

        Store.controller = new Controller(borderPane);
        Store.shelves.add(new Shelve("Heineken", borderPane, 1000, 400));
        Store.shelves.add(new Shelve("Coca Cola", borderPane, 1200, 400));
        Store.shelves.add(new Shelve("Spa Rood", borderPane, 1400, 400));
        Store.shelves.add(new Shelve("Pikachu", borderPane, 1600, 400));
        Store.shelves.add(new Shelve("X", borderPane, 1600, 600));
        Store.shelves.add(new Shelve("Rode Wijn", borderPane, 1000, 600));
        Store.shelves.add(new Shelve("Melk", borderPane, 1200, 600));
        Store.shelves.add(new Shelve("Jus d'Orange", borderPane, 1400, 600));
        Store.removeDeadShelves(borderPane);


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
                }

                controller.spawnCustomers(borderPane);


            }
        };
        gameLoop.start();

        primaryStage.show();
    }
}