/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.graphics;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
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

    public DropBlock(double size, Color color, double shade) {
        dropBlock = new Cube(size, color, shade, false);
        getChildren().add(dropBlock.getCube(size, color, shade, true));
        setLayoutX(300);
        setLayoutY(300);
        getDropBlockXAnimation();
        animation.play();
    }
    
    public final void getDropBlockXAnimation() {
        animation = new Timeline();
        
        KeyValue dbXStartValue = new KeyValue(dropBlock.rx.angleProperty(), 0);
        KeyValue dbYStartValue = new KeyValue(dropBlock.ry.angleProperty(), 0);
        KeyValue dbZStartValue = new KeyValue(dropBlock.rz.angleProperty(), 0);
        
        KeyValue dbXEndValue = new KeyValue(dropBlock.rx.angleProperty(), 360);
        KeyValue dbYEndValue = new KeyValue(dropBlock.ry.angleProperty(), 360);
        KeyValue dbZEndValue = new KeyValue(dropBlock.rz.angleProperty(), 360);
        
        KeyFrame dbStartFrame = new KeyFrame(Duration.ZERO, dbXStartValue,dbYStartValue, dbZStartValue);
        KeyFrame dbEndFrame = new KeyFrame(animationTime, dbXEndValue,dbYEndValue,dbZEndValue);
        
        
        animation.getKeyFrames().addAll(dbStartFrame,dbEndFrame);
        
        
        animation.setCycleCount(Animation.INDEFINITE);
        
    }
}
