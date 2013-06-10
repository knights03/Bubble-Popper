/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.graphics;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import co.knights.games.bubblepopper.Utils;
import co.knights.games.bubblepopper.data.PlayerData;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author hope
 */
public class CircleTarget implements Graphics {

    private int amount;
    private static EventHandler<MouseEvent> die;
    private static Group root;
    private int maxSize;
    private Circle circleTarget;

    public CircleTarget(int amount, int maxSize) {
        this.amount = amount;
        this.maxSize = maxSize;
        root = new Group();
    }

    public static EventHandler<MouseEvent> killTarget(final Circle c) {
        die = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().remove(c);
                PlayerData.score++;
            }
        };

        return die;
    }

   // @Override
   // public final Group draw() {
   //     double minOpacity = 0.75;
   //     Light.Distant light = new Light.Distant(125, 65, Color.LIMEGREEN);
   //     Lighting lighting;
   //     lighting = new Lighting(light);
   //     lighting.setSurfaceScale(8);
   //     Effect blur = new GaussianBlur(13);
   //     root = new Group();
   //     Random r = new Random();
   //     for (int i = 0; i < 30; i++) {
//
   //         Circle c = new Circle();
   //         Interpolator xInterp = Interpolator.TANGENT(Duration.seconds(r.nextInt(30)), r.nextDouble(), Duration.seconds(r.nextInt(30)), r.nextDouble());
   //         Interpolator yInterp = Interpolator.TANGENT(Duration.seconds(r.nextInt(30)), r.nextDouble(), Duration.seconds(r.nextInt(30)), r.nextDouble());
   //         int x = r.nextInt((int) Utils.WIDTH - 300);
   //         int y = r.nextInt((int) Utils.HEIGHT);
   //         c.setLayoutX(x);
   //         c.setLayoutY(y);
   //         c.setRadius(maxSize);
   //         c.setFill(Color.rgb(Utils.getRandomInt(255), Utils.getRandomInt(255), Utils.getRandomInt(255)));
   //         root.getChildren().add(c);
   //         c.setOnMouseClicked(killTarget(c));
   //         int xend = r.nextInt(400) + 400;
   //         int yend = r.nextInt(400) + 400;
//
   //         Duration waitTime = Duration.seconds(r.nextInt(7));
   //         Duration xAni = Duration.seconds(30);
   //         Duration yAni = Duration.seconds(30);
   //         c.setOpacity(r.nextDouble());
   //         if (c.getOpacity() < minOpacity - 0.01) {
   //             c.setOpacity(minOpacity);
   //         }
//
   //         Timeline cirAnimation = new Timeline();
   //         KeyValue cXKv = new KeyValue(c.layoutXProperty(), xend, xInterp);
   //         KeyValue cYKv = new KeyValue(c.layoutYProperty(), yend, yInterp);
   //         KeyFrame xAnimation = new KeyFrame(xAni, cXKv);
   //         KeyFrame yAnimation = new KeyFrame(yAni, cYKv);
   //         cirAnimation.getKeyFrames().addAll(xAnimation, yAnimation);
//
//
   //         //c.setEffect(lighting);
   //         c.setEffect(blur);
   //         c.setStroke(Color.RED);
   //         cirAnimation.setAutoReverse(true);
   //         cirAnimation.setCycleCount(Animation.INDEFINITE);
   //         cirAnimation.setDelay(waitTime);
//
   //         cirAnimation.play();
   //     }
   //     return root;
   // }

    @Override
    public Group draw() {
        
        for (int i = 0; i < amount; i++) {
            circleTarget = new Circle((double)Utils.getRandomInt((int)Utils.WIDTH - 300), (double)Utils.getRandomInt((int)Utils.HEIGHT), (double)Utils.getRandomInt((int)maxSize) + 20, Color.rgb(Utils.getRandomInt(255), Utils.getRandomInt(255), Utils.getRandomInt(255)));
            circleTarget.setOnMouseClicked(killTarget(circleTarget));
            root.getChildren().add(circleTarget);
        }
        
        return root;
    }
}
