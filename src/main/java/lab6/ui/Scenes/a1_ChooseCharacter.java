package lab6.ui.Scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab6.Entities.Entity;
import lab6.Entities.SoldierStuff.Soldier;
import lab6.ui.FXMain;

import java.net.URL;

import static lab6.ui.SceneProperties.ScrollBorder.BorderType.*;
import static lab6.ui.SceneProperties.ScrollBorder.scrollBorder;

public class a1_ChooseCharacter {

    public static Scene chooseCharacter(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Text leftBorder = scrollBorder(XCOM);
        Text rightBorder = scrollBorder(XCOM);
        Text main = new Text();

        Button character1 = new Button("Solider 1");

        Button character2 = new Button("Solider 2");

        Button character3 = new Button("Solider 3");


        // place bradford first line before the first mission
        URL videoPath = FXMain.class.getResource("/bradford_line_1.mp3");
        String stringVideoPath = videoPath.toExternalForm();
        Media media = new Media(stringVideoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(character1, character2, character3);
        buttonContainer.setAlignment(Pos.CENTER);

        root.setBottom(buttonContainer);
        root.setCenter(main);
        root.setLeft(leftBorder);
        root.setRight(rightBorder);


        root.setStyle("-fx-background-color: black");
        return new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
    }


    private void pickCharacter() {

    }

    private void generateSolider() {
        Entity solider = new Soldier();
    }


}
