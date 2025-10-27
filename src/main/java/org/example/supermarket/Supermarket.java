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
        Store.entrance = new Entrance(borderPane, 1500.0, 50.0);
        Store.exit = new Exit(borderPane, 300.0, 50.0);
        Store.storage = new Storage(borderPane, 1200.0, 900.0);
        Store.moneyCounter = new MoneyCounter(borderPane, 1000.0, 100.0);
        Store.moneyCounter.update();
        Controller controller = new Controller(borderPane);



        // Meerdere checkouts
        Store.checkouts.add(new Checkout("C1", borderPane, 500, 400));
        Store.checkouts.add(new Checkout("C2", borderPane, 500, 600));
        Store.checkouts.add(new Checkout("C3", borderPane, 500, 800));

        Store.controller = new Controller(borderPane);
        ProductCatalog catalog = new ProductCatalog();
        List<Product> products = catalog.getAllProducts();
        int[] xPositions = {1000, 1200, 1400, 1600};
        int[] yPositions = {300, 500, 700};
        int index = 0;
        for (Product product : products) {
            if (index < xPositions.length * yPositions.length) {
                int x = xPositions[index % xPositions.length];
                int y = yPositions[index / xPositions.length];
                Store.shelves.add(new Shelve(product.getName(), borderPane, x, y, 10));
                index++;
            }
        }
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

                if (Store.restockers.isEmpty())
                {
                    Store.restockers.add(new Restocker("Bart", borderPane));
                    Store.restockers.add(new Restocker("Bart", borderPane));
                    Store.restockers.add(new Restocker("Bart", borderPane));
                }

                controller.spawnCustomers(borderPane);
                controller.checkRestockableShelves();

                for (Restocker r : Store.restockers) {
                    r.update();
                }



            }
        };
        gameLoop.start();

        primaryStage.show();
    }
}