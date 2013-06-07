/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.bgs;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import co.knights.games.bubblepopper.Utils;

/**
 *
 * @author hope
 */
public class BackGround {

    private int currentState;
    private Group root;

    public BackGround(int lvl, Group root) {
        this.currentState = lvl;
        this.root = root;
    }

    public static Group setLevel1() {
        int circleAmount = 40;
        int ii = 0;
        //Circle Lighting
        Light.Distant light = new Light.Distant(125, 65, Color.LIMEGREEN);
        Lighting lighting;
        lighting = new Lighting(light);
        lighting.setSurfaceScale(8);
        Effect blur = new GaussianBlur(13);

        Group g = new Group();
        final Circle circle = new Circle();
        Random r = new Random();
        Interpolator interp = Interpolator.TANGENT(Duration.seconds(r.nextInt(30)), r.nextDouble(), Duration.seconds(r.nextInt(30)), r.nextDouble());
        Circle[] cgroup = new Circle[circleAmount];
        
        for (Circle c : cgroup) {
            c = new Circle(r.nextInt((int)Utils.WIDTH), r.nextInt((int)Utils.HEIGHT), r.nextInt(50) + 30, Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255), r.nextDouble()));
            
            Timeline t = new Timeline();
            t.getKeyFrames().addAll(new KeyFrame(Duration.seconds(r.nextInt(10)), new KeyValue(c.centerXProperty(), r.nextInt(800), interp)),
                    new KeyFrame(Duration.seconds(r.nextInt(10)), new KeyValue(c.centerYProperty(), r.nextInt(800), interp)));
            t.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    
                }
            });
            
            //c.setEffect(lighting);
            c.setEffect(blur);
            t.setAutoReverse(true);
            t.setCycleCount(Animation.INDEFINITE);
            t.setDelay(Duration.seconds(r.nextInt(7)));
            t.play();
            g.getChildren().add(c);
           
            
        }
        return g;
    }
}
