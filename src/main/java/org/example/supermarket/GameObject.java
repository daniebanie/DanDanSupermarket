package org.example.supermarket;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * basisobject voor alle elementen in de supermarkt.
 * zorgt voor een beeld met een positie, kleur en naam.
 */
public class GameObject {

    /** naam van het object */
    String objectName;

    /** container waarin de grafische elementen van het object worden geplaatst */
    StackPane stack = new StackPane();

    /** random generator voor eventueel willekeurig gedrag */
    public Random rand = new Random();

    /** optionele healthbar die gekoppeld kan worden aan het object */
    public Healthbar healthbar;

    /** rechthoek die het object visueel weergeeft */
    protected Rectangle rectangle;

    /**
     * maakt een nieuw gameobject aan en voegt dit toe aan het scherm
     *
     * @param borderPane het pane waarin het object wordt weergegeven
     * @param objectName de naam van het object
     * @param color de kleur van het object
     * @param x x-positie van het object
     * @param y y-positie van het object
     * @param width breedte van het object
     * @param height hoogte van het object
     */
    public GameObject(BorderPane borderPane, String objectName, Color color, double x, double y, double width, double height) {
        this.objectName = objectName;
        rectangle = new Rectangle(x, y, width, height);
        rectangle.fillProperty().set(color);
        rectangle.setStroke(Color.BLACK);

        stack.getChildren().addAll(rectangle);
        stack.setLayoutX(x);
        stack.setLayoutY(y);
        stack.setAlignment(Pos.TOP_LEFT);

        borderPane.getChildren().add(stack);
    }

    /**
     * geeft de huidige x-positie van het object terug
     *
     * @return x-positie
     */
    double getX() {
        return stack.getLayoutX();
    }

    /**
     * geeft de huidige y-positie van het object terug
     *
     * @return y-positie
     */
    double getY() {
        return stack.getLayoutY();
    }

    /**
     * stelt een nieuwe naam in voor het object
     *
     * @param name nieuwe naam
     */
    public void setName(String name) {
        this.objectName = name;
    }

    /**
     * geeft de naam van het object terug
     *
     * @return naam van het object
     */
    public String getName() {
        return this.objectName;
    }
}
