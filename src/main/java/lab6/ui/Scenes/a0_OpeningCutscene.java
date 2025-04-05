package lab6.ui.Scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import lab6.Entities.SoldierStuff.Soldier;
import lab6.ui.FXMain;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static lab6.Entities.SoldierStuff.SoliderAttributes.ClassType.*;
import static lab6.Entities.SoldierStuff.SoliderAttributes.ClassType.SHARPSHOOTER;
import static lab6.ui.Scenes.a1_MissionBrief.missionBriefing;

public class a0_OpeningCutscene {

    public static Scene getOpeningScene(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Button skipButton = new Button("Skip Cutscene");
        skipButton.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 25; -fx-background-color: black; -fx-border-color: white; -fx-border-width: 5");
        skipButton.setAlignment(Pos.CENTER);

        URL videoPath = FXMain.class.getResource("/xcom_opening_final.mp4");
        String stringVideoPath = videoPath.toExternalForm();
        Media media = new Media(stringVideoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        mediaPlayer.setOnEndOfMedia(() -> { // video plays fully
            videoOver(mediaPlayer, primaryStage);
        });

        mediaPlayer.setOnError(() -> { // cutscene doesnt load
            reloadMedia(mediaPlayer, mediaView, stringVideoPath, primaryStage);
        });

        skipButton.setOnAction(actionEvent -> { // cutscene skipped
            videoOver(mediaPlayer, primaryStage);
        });

        root.setCenter(mediaView);
        root.setBottom(skipButton);
        root.setStyle("-fx-background-color: black");
        return new

                Scene(root);
    }

    private static List<Soldier> createSquad() {
        Soldier specialist = new Soldier(SPECIALIST);
        Soldier ranger = new Soldier(RANGER);
        Soldier grenadier = new Soldier(GRENADIER);
        Soldier sharpShooter = new Soldier(SHARPSHOOTER);

        return new ArrayList<>(List.of(
                specialist, ranger, grenadier, sharpShooter));
    }

    private static void videoOver(MediaPlayer mediaPlayer, Stage primaryStage) {
        /**
         * setting a new scene on our stage can cause some slight sizing problems between the switch
         * to get around leaving and reentering fullscreen mode we instead swap the root of the scene instead of he scene itself
         * this keeps the scene size untouchedSSS
         */
        mediaPlayer.dispose();
        BorderPane missionBrief = missionBriefing(primaryStage, createSquad(), "First");
        primaryStage.getScene().setRoot(missionBrief); // replace stage root with the new root
    }

    private static void reloadMedia(MediaPlayer mediaPlayer, MediaView mediaView, String url, Stage primaryStage) {
        mediaPlayer.dispose();

        Media newMedia = new Media(url);
        MediaPlayer newMediaPlayer = new MediaPlayer(newMedia);

        newMediaPlayer.setAutoPlay(true);

        newMediaPlayer.setOnEndOfMedia(() -> {
                    videoOver(newMediaPlayer, primaryStage);
                }
        );

        newMediaPlayer.setOnError(() -> {
                    reloadMedia(newMediaPlayer, mediaView, url, primaryStage);
                }
        );

        mediaView.setMediaPlayer(newMediaPlayer);
    }
}
