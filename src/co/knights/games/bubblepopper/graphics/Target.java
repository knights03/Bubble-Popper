/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.graphics;

import co.knights.games.bubblepopper.Utils;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

/**
 *
 * @author hope
 */
public abstract class Target extends Parent implements Graphics {

    private double size;
    private Color color;
    private double x, y;
    private boolean isAlive = true;

    public Target() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public final void setSize(double size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setRoot(Group root) {
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
