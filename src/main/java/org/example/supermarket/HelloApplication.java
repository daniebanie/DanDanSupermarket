package org.example.supermarket;

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
        Scene scene = new Scene(borderPane, 1980, 1080);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DanDan");

        List<Customer>  customers = new ArrayList<>();

        Customer c1 = new Customer("Test", "23e1", borderPane);
        Customer c2 = new Customer("Test1", "23e2", borderPane);
        Customer c3 = new Customer("Test2", "23e3", borderPane);
        Customer c4 = new Customer("Test3", "23e4", borderPane);
        Customer c5 = new Customer("Test5", "23e5", borderPane);
        Shelve s1 = new Shelve("Shelve1", borderPane, 500, 400);
        Shelve s2 = new Shelve("Shelve2", borderPane, 800, 500);

        c1.moveTo(200,300);
        c2.moveTo(200, 100);
        c3.moveTo(100,200);
        c4.moveTo(300, 200);
        c5.moveTo(s2.getX(), s2.getY());
        primaryStage.show();
    }
}



