package lab6.ui.Scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import lab6.ui.FXMain;

import java.net.URL;

public class a0_OpeningCutscene {

    public static Scene getOpeningScene(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Button skipButton = new Button("Skip Cutscene");

        URL videoPath = FXMain.class.getResource("/xcom_opening_final.mp4");
        String stringVideoPath = videoPath.toExternalForm();
        Media media = new Media(stringVideoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        mediaPlayer.setOnEndOfMedia(() -> { // video plays fully
            videoOver(mediaPlayer, primaryStage);
        });

        mediaPlayer.setOnStopped(() -> { // cutscene skipped
            videoOver(mediaPlayer, primaryStage);
        });

        skipButton.setOnAction(e -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                // stop pre-mature presses from breaking stuff
                mediaPlayer.stop();
            }
        });

        root.setCenter(mediaView);
        root.setBottom(skipButton);
        skipButton.setAlignment(Pos.BOTTOM_CENTER);
        root.setStyle("-fx-background-color: black");
        return new Scene(root, 800, 800);
    }

    private static void videoOver(MediaPlayer mediaPlayer, Stage primaryStage) {
        mediaPlayer.dispose();
        Scene chooseCharacterScene = a1_ChooseCharacter.chooseCharacter(primaryStage);
        primaryStage.setScene(chooseCharacterScene);
        primaryStage.setFullScreen(true);
    }
}