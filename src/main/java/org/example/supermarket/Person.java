package org.example.supermarket;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * abstracte klasse die een persoon in de supermarkt voorstelt.
 * bevat logica om te bewegen naar een doelpositie en controleren of de bestemming bereikt is.
 */
abstract class Person extends GameObject {

    /** doel-x positie waar de persoon naartoe loopt */
    protected double targetX;

    /** doel-y positie waar de persoon naartoe loopt */
    protected double targetY;

    /** loopsnelheid van de persoon (pixels per seconde) */
    protected double walkingSpeed = 200.0;

    /**
     * maakt een nieuwe persoon aan op de opgegeven positie
     *
     * @param name naam van de persoon
     * @param borderPane het pane waarin de persoon weergegeven wordt
     * @param color kleur van de persoon
     * @param x start x-positie
     * @param y start y-positie
     * @param width breedte van de persoon
     * @param height hoogte van de persoon
     */
    public Person(String name, BorderPane borderPane, Color color, double x, double y, double width, double height) {
        super(borderPane, name, color, x, y, width, height);
    }

    /**
     * laat de persoon bewegen naar een doelpositie
     *
     * @param x doel x-positie
     * @param y doel y-positie
     */
    public void moveTo(double x, double y) {
        this.targetX = x;
        this.targetY = y;
        double time = distanceBetweenPoints(stack.getLayoutX(), stack.getLayoutY(), x, y) / this.walkingSpeed;
        KeyValue keyValueX = new KeyValue(stack.layoutXProperty(), x);
        KeyValue keyValueY = new KeyValue(stack.layoutYProperty(), y);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), keyValueX, keyValueY);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    /**
     * berekent de afstand tussen twee punten
     *
     * @param x1 x-coördinaat van het eerste punt
     * @param y1 y-coördinaat van het eerste punt
     * @param x2 x-coördinaat van het tweede punt
     * @param y2 y-coördinaat van het tweede punt
     * @return afstand tussen de twee punten
     */
    public double distanceBetweenPoints(double x1, double y1, double x2, double y2) {
        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);
        return Math.hypot(ac, cb);
    }

    /**
     * controleert of de persoon bij de bestemming is aangekomen
     *
     * @return true als de persoon dicht genoeg bij het doel is, anders false
     */
    public boolean isAtDestination() {
        double dx = stack.getLayoutX() - targetX;
        double dy = stack.getLayoutY() - targetY;
        return Math.hypot(dx, dy) < 5;
    }
}
