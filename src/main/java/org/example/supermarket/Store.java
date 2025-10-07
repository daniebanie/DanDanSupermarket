package org.example.supermarket;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public static Controller controller;
    public static Entrance entrance;
    public static Exit exit;
    public static List<Shelve> shelves = new ArrayList<Shelve>();
    public static List<Checkout> checkouts = new ArrayList<Checkout>();
    public static List<Customer> customers  = new ArrayList<Customer>();
}
