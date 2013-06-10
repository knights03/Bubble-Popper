/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.graphics;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author hope
 */
public class DropBlock extends Group {

    private Timeline animation;
    private Duration animationTime = Duration.seconds(10);
    private Cube dropBlock;

    //private Cube dropBlock;
    public DropBlock(double size, Color color, double shade) {

        dropBlock = new Cube(size, color, 0, 0 ,0);
        getChildren().add(dropBlock.getCube(size, Color.IVORY, shade, false));
        setLayoutX(300);
        setLayoutY(300);
        getDropBlockXAnimation(dropBlock);
        
        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                animation.play();
                }
            
        });
        
        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                animation.pause();
            }
        });
        
        setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                getChildren().clear();
            }
        });

    }

    public final void getDropBlockXAnimation(Cube dropBlock) {
        animation = new Timeline();

        KeyValue dbXStartValue = new KeyValue(dropBlock.rx.angleProperty(), 0);
        KeyValue dbYStartValue = new KeyValue(dropBlock.ry.angleProperty(), 0);
        KeyValue dbZStartValue = new KeyValue(dropBlock.rz.angleProperty(), 0);

        KeyValue dbXEndValue = new KeyValue(dropBlock.rx.angleProperty(), 360);
        KeyValue dbYEndValue = new KeyValue(dropBlock.ry.angleProperty(), 360);
        KeyValue dbZEndValue = new KeyValue(dropBlock.rz.angleProperty(), 360);

        KeyFrame dbStartFrame = new KeyFrame(Duration.ZERO, dbXStartValue, dbYStartValue, dbZStartValue);
        KeyFrame dbEndFrame = new KeyFrame(animationTime, dbXEndValue, dbYEndValue, dbZEndValue);


        animation.getKeyFrames().addAll(dbStartFrame, dbEndFrame);


        animation.setCycleCount(Animation.INDEFINITE);

    }
}
