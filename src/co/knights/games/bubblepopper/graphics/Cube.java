/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.graphics;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;

/**
 *
 * @author hope
 */
public final class Cube {

    private Rectangle frontSide;
    private Rectangle leftSide;
    private Rectangle rightSide;
    private Rectangle topSide;
    private Rectangle backSide;
    private Rectangle bottomSide;
    
    private double x,y;
    private double xTilt,yTilt,zTilt;
    public Rotate rx = new Rotate(xTilt, Rotate.X_AXIS);
    public Rotate ry = new Rotate(yTilt, Rotate.Y_AXIS);
    public Rotate rz = new Rotate(zTilt, Rotate.Z_AXIS);

    public Cube(double size, Color color, double xTilt, double yTilt, double zTilt) {
        this.xTilt = xTilt;
        this.yTilt = yTilt;
        this.zTilt = zTilt;
        
        rx.angleProperty().set(xTilt);
        ry.angleProperty().set(yTilt);
        rz.angleProperty().set(zTilt);
        
        getCube(size, color, 1, false);
    }

    public void setxTilt(double xTilt) {
        this.xTilt = xTilt;
        rx.angleProperty().set(xTilt);
    }

    public void setyTilt(double yTilt) {
        this.yTilt = yTilt;
        ry.angleProperty().set(yTilt);
    }

    public void setzTilt(double zTilt) {
        this.zTilt = zTilt;
        rz.angleProperty().set(zTilt);
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
        final Group g = new Group();
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
                .strokeWidth(3)
                .build();


        rightSide = RectangleBuilder.create() // left face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.2 * shade), 1.0))
                .translateX(0)
                .translateY(-0.5 * size)
                .rotationAxis(Rotate.Y_AXIS)
                .rotate(90)
                .stroke(stroke)
                .strokeWidth(3)
                .build();


        leftSide = RectangleBuilder.create() // right face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.3 * shade), 1.0))
                .translateX(-1 * size)
                .translateY(-0.5 * size)
                .rotationAxis(Rotate.Y_AXIS)
                .rotate(90)
                .stroke(stroke)
                .strokeWidth(3)
                .build();


        topSide = RectangleBuilder.create() // top face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.1 * shade), 1.0))
                .translateX(-0.5 * size)
                .translateY(-1 * size)
                .rotationAxis(Rotate.X_AXIS)
                .rotate(90)
                .stroke(stroke)
                .strokeWidth(3)
                .build();


        bottomSide = RectangleBuilder.create() // bottom face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.4 * shade), 1.0))
                .translateX(-0.5 * size)
                .translateY(0)
                .rotationAxis(Rotate.X_AXIS)
                .rotate(90)
                .stroke(stroke)
                .strokeWidth(3)
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
        
        g.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.W){
                    
                }
            }
        });
        return g;
    }
}
