package lab6.ui.Scenes;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.Entity;
import lab6.Entities.SoldierStuff.Soldier;
import lab6.Entities.SoldierStuff.SoliderAttributes;
import lab6.Utility.GetRandom;
import lab6.ui.FXMain;

import java.net.URL;
import java.util.*;

import static lab6.ui.SceneProperties.BillboardHeading.heading;
import static lab6.ui.SceneProperties.ScrollBorder.BorderType.*;
import static lab6.ui.SceneProperties.ScrollBorder.scrollBorder;

public class a1_MissionBrief {

    public static Scene missionBriefing(Stage primaryStage, List<Soldier> squad) {
        BorderPane root = new BorderPane();
        Text leftBorder = scrollBorder(XCOM);
        Text rightBorder = scrollBorder(XCOM);
        Text main = new Text(missionDescription());
        main.setTextAlignment(TextAlignment.CENTER);
        main.setStyle("-fx-font-weight: bold; -fx-font-size: 15pt");
        main.setFill(Color.WHITE);
        Button start = new Button("Start Mission");

        URL videoPath = FXMain.class.getResource("/bradford_line_1.mp3");
        String stringVideoPath = videoPath.toExternalForm();
        Media media = new Media(stringVideoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        start.addEventHandler(Event.ANY, event -> {
            if (squad == null) {
                videoOver(mediaPlayer, primaryStage, createSquad());
            } else {
                videoOver(mediaPlayer, primaryStage, squad);

            }
        });

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(start);
        buttonContainer.setAlignment(Pos.CENTER);

        TextFlow heading = heading("MISSION BRIEFING");


        root.setBottom(buttonContainer);
        root.setCenter(main);
        root.setLeft(leftBorder);
        root.setRight(rightBorder);
        root.setTop(heading);

        root.setStyle("-fx-background-color: black");
        return new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
    }


    private static List<Soldier> createSquad() {
        // create the users squad. This squad stays with them for the entire game
        List<Soldier> squad = new ArrayList<>(List.of(
                new Soldier(SoliderAttributes.ClassType.SPECIALIST),
                new Soldier(SoliderAttributes.ClassType.RANGER),
                new Soldier(SoliderAttributes.ClassType.GRENADIER),
                new Soldier(SoliderAttributes.ClassType.SHARPSHOOTER)
        ));
        return squad;
    }

    private static List<Alien> createEnemies() {
        List<Alien> enemyGroup = new ArrayList<>(List.of(
        ));
        return enemyGroup;
    }

    private static String missionDescription() {
        int type = GetRandom.IntInRange(1, 5);
        String description = "";
        switch (type) {
            case 1 -> description = "a distress call from a nearby resistance group. We need to get in their and help them before ADVENT kills more of our allies";

            case 2 -> description = "a broadcast from an Alien Relay. We need to destroy this relay before the  transmission completes";

            case 3 -> description = "intel of a Supply Raid we can hit. A resistance group managed to stop an ADVENT train, we should get in there and recover resources.";

            case 4 -> description = "intel of a Landed UFO. If we attack now we can gather valuable intel and resources from their ship.";

            case 5 -> description = "word of a Alien Facility. Losing this facility would hit ADVENT hard.";
        }


        return String.format(
                "Central Officer Bradford:\nCommander!\nWe have received %s.", description);
    }

    private static void videoOver(MediaPlayer mediaPlayer, Stage primaryStage, List<Soldier> squad) {
        mediaPlayer.dispose();
        primaryStage.setFullScreen(true);
    }
}
