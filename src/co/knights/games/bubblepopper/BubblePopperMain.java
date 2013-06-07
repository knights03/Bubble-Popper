
package co.knights.games.bubblepopper;

import java.awt.Toolkit;
import java.util.Random;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import co.knights.games.bubblepopper.bgs.BackGround;
import co.knights.games.bubblepopper.data.PlayerData;
import co.knights.games.bubblepopper.graphics.CircleTarget;


public class BubblePopperMain extends Application {
    private LettersPane lettersPane;
    private BackGround bg;
    private CircleTarget target;
    private GameLoop loop;
    private boolean running = false;
    private Group root;
    public State state;
    private Listener l;

    private void init(Stage primaryStage) {
        running = true;
        state = State.LEVEL_ONE;
        l = new Listener();
        Utils.WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Utils.HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        Scene s = new Scene(root, Color.BLACK);
        s.setOnKeyPressed(l.getKeyListener());
        primaryStage.setScene(s);
        primaryStage.setTitle(Utils.TITLE);
        lettersPane = new LettersPane();
        bg = new BackGround(1, root);
        target = new CircleTarget(55);
        loop = new GameLoop(60, root);
        loop.beginGameLoop();
        
        
        root.getChildren().addAll(target.drawLevelOne(), new PlayerData("erick"));
    }
    
    

    public void play() {
        // request focus so we get key events
        Platform.runLater(new Runnable() {
            @Override public void run() {
                lettersPane.requestFocus();
            }
        });
    }

    public static class LettersPane extends Region {
        private static final Font FONT_DEFAULT = new Font(Font.getDefault().getFamily(), 200);
        private static final Random RANDOM = new Random();
        private static final Interpolator INTERPOLATOR = Interpolator.TANGENT(Duration.millis(1500), 0.25, Duration.millis(1500), 0.25);
        private Text pressText;

        public LettersPane() {
            setId("LettersPane");
            setFocusTraversable(true);
            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    requestFocus();
                }
            });
            setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    createLetter(ke.getText());
                }
            });
            // create press keys text
            pressText = new Text("Press Keys");
            pressText.setTextOrigin(VPos.TOP);
            pressText.setFont(new Font(Font.getDefault().getFamily(), 100));
            pressText.setLayoutY(5);
            pressText.setFill(Color.AQUA);
            DropShadow effect = new DropShadow();
            effect.setRadius(0);
            effect.setOffsetY(1);
            effect.setColor(Color.WHITE);
            pressText.setEffect(effect);
        }

        @Override protected void layoutChildren() {
            // center press keys text
            pressText.setLayoutX((getWidth() - pressText.getLayoutBounds().getWidth()) / 2);
        }

        private void createLetter(String c) {
            Random r = new Random();
            final Text letter = new Text(c);
            letter.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            letter.setFont(FONT_DEFAULT);
            letter.setTextOrigin(VPos.TOP);
            letter.setTranslateX((getWidth() - letter.getBoundsInLocal().getWidth()) / 2);
            letter.setTranslateY((getHeight() - letter.getBoundsInLocal().getHeight()) / 2);
            getChildren().add(letter);
            // over 3 seconds move letter to random position and fade it out
            final Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(1500), new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent event) {
                            // we are done remove us from scene
                            getChildren().remove(letter);
                        }
                    },
                    new KeyValue(letter.translateXProperty(), getRandom(0.0f, getWidth() - letter.getBoundsInLocal().getWidth()),INTERPOLATOR),
                    new KeyValue(letter.translateYProperty(), getRandom(0.0f, getHeight() - letter.getBoundsInLocal().getHeight()),INTERPOLATOR),
                    new KeyValue(letter.opacityProperty(), 0f)
            ));
            timeline.play();
        }

        private static float getRandom(double min, double max) {
            return (float)(RANDOM.nextFloat() * (max - min) + min);
        }
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
