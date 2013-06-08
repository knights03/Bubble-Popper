/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.graphics;

import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;

/**
 *
 * @author hope
 */
public class Cube {

    public Rotate rx = new Rotate(0, Rotate.X_AXIS);
    public Rotate ry = new Rotate(0, Rotate.Y_AXIS);
    public Rotate rz = new Rotate(0, Rotate.Z_AXIS);
    public Rectangle frontSide;
    public Rectangle leftSide;
    public Rectangle rightSide;
    public Rectangle topSide;
    public Rectangle backSide;
    public Rectangle bottomSide;

    public Cube(double size, Color color, double shade, boolean effects) {
    }

    public Rotate getRx() {
        return rx;
    }

    public Rotate getRy() {
        return ry;
    }

    public Rotate getRz() {
        return rz;
    }

    public Rectangle getFrontSide() {
        return frontSide;
    }

    public Rectangle getLeftSide() {
        return leftSide;
    }

    public Rectangle getRightSide() {
        return rightSide;
    }

    public Rectangle getTopSide() {
        return topSide;
    }

    public Rectangle getBackSide() {
        return backSide;
    }

    public Rectangle getBottomSide() {
        return bottomSide;
    }

    public Group getCube(double size, Color color, double shade, boolean effects) {
        Group g = new Group();
        BoxBlur effect = new BoxBlur(size * 2, size * 2, 1);
        Color stroke = color;
        g.getTransforms().addAll(rz, ry, rx);

        frontSide = RectangleBuilder.create() // top face
                .width(size).height(size)
                .fill(color)
                .translateX(-0.5 * size)
                .translateY(-0.5 * size)
                .translateZ(-0.5 * size)
                .stroke(stroke)
                .build();


        backSide = RectangleBuilder.create() // back face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.5 * shade), 1.0))
                .translateX(-0.5 * size)
                .translateY(-0.5 * size)
                .translateZ(0.5 * size)
                .stroke(stroke)
                .build();


        rightSide = RectangleBuilder.create() // left face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.2 * shade), 1.0))
                .translateX(0)
                .translateY(-0.5 * size)
                .rotationAxis(Rotate.Y_AXIS)
                .rotate(90)
                .stroke(stroke)
                .build();


        leftSide = RectangleBuilder.create() // right face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.3 * shade), 1.0))
                .translateX(-1 * size)
                .translateY(-0.5 * size)
                .rotationAxis(Rotate.Y_AXIS)
                .rotate(90)
                .stroke(stroke)
                .build();


        topSide = RectangleBuilder.create() // top face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.1 * shade), 1.0))
                .translateX(-0.5 * size)
                .translateY(-1 * size)
                .rotationAxis(Rotate.X_AXIS)
                .rotate(90)
                .stroke(stroke)
                .build();


        bottomSide = RectangleBuilder.create() // bottom face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.4 * shade), 1.0))
                .translateX(-0.5 * size)
                .translateY(0)
                .rotationAxis(Rotate.X_AXIS)
                .rotate(90)
                .stroke(stroke)
                .build();



        if (effects) {
            leftSide.setEffect(effect);
            rightSide.setEffect(effect);
            backSide.setEffect(effect);
            bottomSide.setEffect(effect);
            topSide.setEffect(effect);
            frontSide.setEffect(effect);
        }

        g.getChildren().addAll(backSide, bottomSide, frontSide, leftSide, rightSide, topSide);
        return g;
    }
}
