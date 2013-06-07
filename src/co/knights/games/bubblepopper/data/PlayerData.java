/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.data;

import com.sun.javafx.collections.TrackableObservableList;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import co.knights.games.bubblepopper.Utils;
import co.knights.games.bubblepopper.graphics.VisualEffects;

/**
 *
 * @author hope
 */
public class PlayerData extends Group {

    public static Integer score = 0;
    public static Integer targetsLeft;

    private String playerName;
    private double width = Utils.WIDTH / 6;
    private double height = Utils.HEIGHT - 150;
    private double parentX = Utils.WIDTH - width - 15;
    private double parentY = 25;
    private static Text scoreText;
    private ObservableList<Text> scoreListener;

    public PlayerData(String playerName) {
        this.playerName = playerName;
        System.out.println("Player date");
        
        prefWidth(width);
        prefHeight(height);
        setLayoutX(parentX);
        setLayoutY(parentY);

        Rectangle bg = new Rectangle(width, height - 150, Color.YELLOW);
        bg.setArcHeight(25);
        bg.setArcWidth(25);

        VisualEffects.setAzimuth(-25);
        VisualEffects.setLightColor(Color.DARKRED);
        VisualEffects.setLighting(bg);
        getChildren().addAll(bg);
        
        scoreText = new Text();
        initScore();
        setScoreText();
    }

    public static Integer getScore() {
        
        return score;
        
    }
    
    private void listenForScore() {
        setScoreText();
    }

    public static void setScore(Integer score) {
        PlayerData.score = score;
    }

    private void getStatsPane() {
        setScoreText();
        
    }
    
    private void score() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public void update() {
        initScore();
    }

    private void setScoreText() {
        System.out.println(score);
        getChildren().remove(scoreText);
        getChildren().add(scoreText);
    }

    private void initScore() {
        double scoreX = width / 2;
        double scoreY = 35;
        double scale = 2.5;
        
        Text s = new Text("SCORE");
        s.setScaleX(scale );
        s.setScaleY(scale);
        s.setFill(Color.AQUA);
        s.setLayoutX(scoreX - (s.getLayoutBounds().getWidth() / 2));
        s.setLayoutY(scoreY + (s.getLayoutBounds().getHeight() + 15));
        s.setStroke(Color.WHITE);
        s.setStrokeLineCap(StrokeLineCap.ROUND);
        s.setStrokeWidth(0.25);
        s.setStrokeLineJoin(StrokeLineJoin.ROUND);
        s.setStrokeType(StrokeType.CENTERED);
        getChildren().add(s);
        
        scoreText.setText(getScore().toString());
        scoreText.setScaleX(scale);
        scoreText.setScaleY(scale);
        scoreText.setLayoutX(scoreX - (scoreText.getLayoutBounds().getWidth() / 2));
        scoreText.setLayoutY(scoreY);
        scoreText.setStroke(Color.WHITE);
        scoreText.setStrokeLineCap(StrokeLineCap.ROUND);
        scoreText.setStrokeWidth(0.25);
        scoreText.setStrokeLineJoin(StrokeLineJoin.ROUND);
        scoreText.setStrokeType(StrokeType.CENTERED);
    }
}
