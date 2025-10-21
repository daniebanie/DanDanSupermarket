package org.example.supermarket;

import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

public class Shelve extends GameObject{
    public Shelve(String itemName, BorderPane borderPane, double x, double y)
    {
        super(borderPane, itemName, Color.BROWN, x, y, 100, 70);
//        if (ProductCatalog.getProductByName(itemName) != null)
//        {
//            Product product = ProductCatalog.getProductByName(itemName);
//            System.out.println(product);
//        }
//        else {
//            itemName = "X";
//            System.out.println("Product not found");
//        }
    }

    public void removeDeadShelves(Shelve shelve)
    {

    }

}

