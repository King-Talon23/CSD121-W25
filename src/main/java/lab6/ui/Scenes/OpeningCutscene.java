package lab6.ui.Scenes;

import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.BorderPane;

import lab6.ui.FXMain;

import java.net.URL;

public class OpeningCutscene {

    public static Scene getOpeningScene() {
        BorderPane root = new BorderPane();

        URL videoPath = FXMain.class.getResource("/xcom_opening_final.mp4");
        String stringVideoPath = videoPath.toExternalForm();
        Media media = new Media(stringVideoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        root.setCenter(mediaView);
        root.setStyle("-fx-background-color: black");
        return new Scene(root, 800, 800);
    }
}
