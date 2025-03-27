package lab6.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static lab6.ui.Scenes.a0_OpeningCutscene.getOpeningScene;


public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = getOpeningScene(primaryStage);
        primaryStage.setTitle("XCOM 2: Testing");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setFullScreenExitHint(""); // clear the "press esc" hint

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
