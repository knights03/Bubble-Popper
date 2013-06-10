
package co.knights.games.bubblepopper;

import co.knights.games.bubblepopper.bgs.BackGround;
import co.knights.games.bubblepopper.data.PlayerData;
import co.knights.games.bubblepopper.graphics.CircleTarget;
import co.knights.games.bubblepopper.graphics.DropBlock;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class BubblePopperMain extends Application {
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
        root = new Group();
        root.setDepthTest(DepthTest.ENABLE);
        DropBlock db = new DropBlock(100, Color.WHITE, 1);
        target = new CircleTarget(25, 30);
        bg = new BackGround(1, root);
        
        
        setGameSize();
        initStage(primaryStage);
        startGameLoop();
        
        
        root.getChildren().addAll(target.draw(), new PlayerData("erick"));
    }
    
    

    public void play() {
        // request focus so we get key events
        Platform.runLater(new Runnable() {
            @Override public void run() {
            }
        });
    }

    private void startGameLoop() {
        loop = new GameLoop(60, root);
        loop.beginGameLoop();
    }

    private void initStage(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        Scene s = new Scene(root, Color.BLACK);
        s.setOnKeyPressed(l.getKeyListener());
        primaryStage.setScene(s);
        primaryStage.setTitle(Utils.TITLE);
    }

    private void setGameSize() throws HeadlessException {
        Utils.WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Utils.HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
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
