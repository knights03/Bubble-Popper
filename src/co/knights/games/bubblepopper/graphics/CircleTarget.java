/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.graphics;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import co.knights.games.bubblepopper.Utils;
import co.knights.games.bubblepopper.data.PlayerData;

/**
 *
 * @author hope
 */
public class CircleTarget extends Target {

    private Circle target;
    private int amount;
    private static Circle[] cirSet;
    private static EventHandler<MouseEvent> die;
    private static Group root;

    public CircleTarget(int amount) {
        this.amount = amount;
        root = new Group();
        setColor(Color.rgb(Utils.getRandomInt(255), Utils.getRandomInt(255), Utils.getRandomInt(255)));
        setSize((double) Utils.getRandomInt(45) + 45);
    }

    public static EventHandler<MouseEvent> killTarget(final Circle c) {
        die = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(c);
                PlayerData.score++;
            }
        };

        return die;
    }

    @Override
    public final Group drawLevelOne() {
        int circleAmount = 25;
        double minOpacity = 0.75;
        //Circle Lighting
        Light.Distant light = new Light.Distant(125, 65, Color.LIMEGREEN);
        Lighting lighting;
        lighting = new Lighting(light);
        lighting.setSurfaceScale(8);
        Effect blur = new GaussianBlur(13);

        Group circleGroup = new Group();
        Random r = new Random();
        //cirSet = new Circle[circleAmount];

        //for (Circle c : cirSet) {
        for (Integer i = 0; i < circleAmount; i++) {
            Interpolator xInterp = Interpolator.TANGENT(Duration.seconds(r.nextInt(30)), r.nextDouble(), Duration.seconds(r.nextInt(30)), r.nextDouble());
            Interpolator yInterp = Interpolator.TANGENT(Duration.seconds(r.nextInt(30)), r.nextDouble(), Duration.seconds(r.nextInt(30)), r.nextDouble());
            int x = r.nextInt((int) Utils.WIDTH - 300);
            int y = r.nextInt((int) Utils.HEIGHT);

            int xend = r.nextInt(400) + 400;
            int yend = r.nextInt(400) + 400;

            Duration waitTime = Duration.seconds(r.nextInt(7));
            Duration xAni = Duration.seconds(30);
            Duration yAni = Duration.seconds(30);

            Circle c = new Circle(x, y, r.nextInt(50) + 30, Color.rgb(10 + r.nextInt(245), 10 + r.nextInt(245), 10 + r.nextInt(245)));
            c.setOpacity(r.nextDouble());
            if (c.getOpacity() < minOpacity - 0.01) {
                c.setOpacity(minOpacity);
            }
            Timeline cirAnimation = new Timeline();
            KeyValue cXKv = new KeyValue(c.layoutXProperty(), xend, xInterp);
            KeyValue cYKv = new KeyValue(c.layoutYProperty(), yend, yInterp);
            KeyFrame xAnimation = new KeyFrame(xAni, cXKv);
            KeyFrame yAnimation = new KeyFrame(yAni, cYKv);
            cirAnimation.getKeyFrames().addAll(xAnimation, yAnimation);


            //c.setEffect(lighting);
            c.setEffect(blur);
            c.setStroke(Color.RED);
            cirAnimation.setAutoReverse(true);
            cirAnimation.setCycleCount(Animation.INDEFINITE);
            cirAnimation.setDelay(waitTime);
            root.getChildren().add(c);


            //Text targetNumber = new Text(i.toString());
            //targetNumber.setScaleX(c.getRadius() / 7);
            //targetNumber.setScaleY(c.getRadius() / 7);
            //targetNumber.setLayoutX(c.getCenterX());
            //targetNumber.setLayoutY(c.getCenterY());
            //targetNumber.setFill(Color.YELLOW);
            //targetNumber.setOpacity(c.getOpacity());
//
            //Timeline t2 = new Timeline();
            //KeyValue tXKv = new KeyValue(targetNumber.xProperty(), xend, xInterp);
            //KeyValue tYKv = new KeyValue(targetNumber.yProperty(), yend, yInterp);
            //KeyFrame txAnimation = new KeyFrame(xAni, tXKv);
            //KeyFrame tyAnimation = new KeyFrame(yAni, tYKv);
            //t2.getKeyFrames().addAll(txAnimation, tyAnimation);
            //t2.setAutoReverse(true);
            //t2.setCycleCount(Animation.INDEFINITE);
            //t2.setDelay(waitTime);
//
            //root.getChildren().add(targetNumber);

            c.setOnMouseClicked(killTarget(c));
            cirAnimation.play();
            
        }
        return root;


    }
}
