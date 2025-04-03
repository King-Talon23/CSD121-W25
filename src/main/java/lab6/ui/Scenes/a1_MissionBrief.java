package lab6.ui.Scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.SoldierStuff.Soldier;
import lab6.Utility.GetRandom;
import lab6.ui.FXMain;

import java.net.URL;
import java.util.*;

import static lab6.ui.SceneProperties.HeadingDisplay.heading;
import static lab6.ui.SceneProperties.ScrollBorder.BorderType.*;
import static lab6.ui.SceneProperties.ScrollBorder.scrollBorder;
import static lab6.ui.Scenes.a2_CombatScene.combatScene;

public class a1_MissionBrief {

    public static BorderPane missionBriefing(Stage primaryStage, List<Soldier> squad, String audioType) {
        BorderPane root = new BorderPane();
        Text leftBorder = scrollBorder(XCOM);
        Text rightBorder = scrollBorder(XCOM);

        List<Alien> aliens = createEnemies(); // create mission aliens
        MediaPlayer mediaPlayer;
        if (Objects.equals(audioType, "First")) {
            mediaPlayer = firstMissionAudio();
        } else {
            mediaPlayer = null;
        }

        Button start = new Button("Start Mission");
        start.setOnAction(e -> {
            if (mediaPlayer != null) {
                startMission(mediaPlayer, primaryStage, squad, aliens);
            } else {
                startMission(null, primaryStage, squad, aliens);

            }
        });

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(start);
        buttonContainer.setAlignment(Pos.CENTER);

        TextFlow heading = heading("MISSION BRIEFING");

        root.setBottom(buttonContainer);
        root.setCenter(createMain(aliens));
        root.setLeft(leftBorder);
        root.setRight(rightBorder);
        root.setTop(heading);

        root.setStyle("-fx-background-color: black");
        return root;
    }

    private static List<Alien> createEnemies() {
        int amountOfEnemies = GetRandom.intInRange(3, 8);
        List<Alien> enemyGroup = new ArrayList<>();
        for (int i = 0; i < amountOfEnemies; i++) {
            enemyGroup.add(GetRandom.anyAlien()); // generate any alien at random
        }

        return enemyGroup;
    }

    private static void startMission(MediaPlayer mediaPlayer, Stage primaryStage, List<Soldier> squad, List<Alien> enemies) {
        resetSoldiers(squad);
        mediaPlayer.dispose();
        BorderPane combatScene = combatScene(primaryStage, squad, enemies);
        primaryStage.getScene().setRoot(combatScene);
        primaryStage.setFullScreen(true);
    }

    private static Text missionDescription(List<Alien> alienInfo) {
        int type = GetRandom.intInRange(1, 5);
        String description;
        switch (type) {
            case 1 ->
                    description = "a distress call from a nearby resistance group. We need to get in their and help them before ADVENT kills more of our allies";

            case 2 ->
                    description = "a broadcast from an Alien Relay. We need to destroy this relay before the transmission completes";

            case 3 ->
                    description = "intel of a Supply Raid we can hit. A resistance group managed to stop an ADVENT train, we should get in there and recover resources";

            case 4 ->
                    description = "word of a Landed UFO. If we attack now we can gather valuable intel and resources from their ship";

            case 5 ->
                    description = "intel on a Alien Facility. A weak point has been exposed and losing this facility would hit ADVENT hard";

            default -> description = "Error";
        }


        return new Text(String.format(
                "\nCommander!\n\nWe have received %s.\n\nReports indicate %s enemies in the Deployment Zone\nWe're ready to deploy on your order Commander!", description, alienInfo.size()));
    }

    private static Text setUpSpeaker() {
        Text speaker = new Text("Central Officer Bradford");
        speaker.setStyle("-fx-font-weight: bold");
        speaker.setFill(Color.DARKRED);
        return speaker;
    }

    private static TextFlow createMain(List<Alien> aliensOnMission) {
        TextFlow main = new TextFlow(setUpSpeaker());

        Text missionDescription = missionDescription(aliensOnMission);
        missionDescription.setFill(Color.WHITE);

        main.getChildren().add(missionDescription);
        main.setTextAlignment(TextAlignment.CENTER);
        main.setStyle("-fx-font-weight: bold; -fx-font-size: 15pt");
        return main;
    }

    private static MediaPlayer firstMissionAudio() {
        URL videoPath = FXMain.class.getResource("/bradford_line_1.mp3");
        String stringVideoPath = videoPath.toExternalForm();
        Media media = new Media(stringVideoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        return mediaPlayer;

    }

    private static void resetSoldiers(List<Soldier> squad) {
        for (Soldier soldier : squad) {
            soldier.resetStats();
        }
    }


}
