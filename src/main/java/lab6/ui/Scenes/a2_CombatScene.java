package lab6.ui.Scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.SoldierStuff.Cover;
import lab6.Entities.SoldierStuff.Soldier;
import lab6.Utility.GetRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lab6.Entities.SoldierStuff.Cover.*;
import static lab6.ui.SceneProperties.HeadingDisplay.heading;
import static lab6.ui.SceneProperties.ScrollBorder.BorderType.*;
import static lab6.ui.SceneProperties.ScrollBorder.scrollBorder;

public class a2_CombatScene {

    public static BorderPane combatScene(Stage primaryStage, List<Soldier> squad, List<Alien> enemies) {
        BorderPane root = new BorderPane();
        Text leftBorder = scrollBorder(XCOM);
        Text rightBorder = scrollBorder(ALIEN);


        Text topPlaceHolder = new Text("TOP");
        topPlaceHolder.setFill(Color.WHITE);
        Text bottomPlaceHolder = new Text("BOTTOM");
        bottomPlaceHolder.setFill(Color.WHITE);


        VBox mainText = new VBox();
        mainText.setAlignment(Pos.CENTER);

        TextFlow mainTop = new TextFlow(topPlaceHolder); // this will house character voice lines
        TextFlow mainBottom = new TextFlow(bottomPlaceHolder); // this will have game descriptions

        mainTop.setTextAlignment(TextAlignment.CENTER);

        mainBottom.setStyle("-fx-font-weight: bold; -fx-font-size: 15; pt-fx-border-color: white");
        mainTop.setStyle("-fx-font-weight: bold; -fx-font-size: 15; pt-fx-border-color: white");
        mainBottom.setTextAlignment(TextAlignment.CENTER);

        mainText.setFillWidth(true);

        mainTop.autosize();
        mainBottom.autosize();

        TextFlow heading = heading(operationName()); // create heading with random title

        root.setCenter(mainText);
        root.setLeft(leftBorder);
        root.setRight(rightBorder);
        root.setTop(heading);

        root.setStyle("-fx-background-color: black");
        return root;
    }

    private void playerTurn(VBox Vbox, BorderPane root, List<Soldier> squad, List<Alien> enemies) {
        root.setLeft(scrollBorder(XCOM));
        root.setRight(scrollBorder(XCOM));
        // root.setBottom(Button panel);
        for (Soldier soldier : squad) {
            if (!soldier.isAlive) {
                squad.remove(soldier);
                continue;
            }
            if (soldier.hunkerBonus) {
                soldier.removeHunkerBonus();
            }
            soldier.resetActionPoints();
            if (soldier.hasTurn) {

                soldier.actionPoints--;
                if (soldier.actionPoints <= 0) {
                    soldier.hasTurn = false;
                }
            }
        }
    }

    private static String operationName() { // the title for the header
        List<String> adjectives = new ArrayList<>(Arrays.asList(
                "Radiant", "Luminous", "Serene", "Vibrant", "Zesty", "Bold", "Melancholy", "Fierce",
                "Majestic", "Whimsical", "Gallant", "Dazzling", "Pristine", "Mysterious", "Jubilant",
                "Dynamic", "Blissful", "Rugged", "Timid", "Curious", "Sassy", "Agile", "Intrepid",
                "Jovial", "Elated", "Graceful", "Robust", "Harmonious", "Tranquil", "Tenacious",
                "Passionate", "Quaint", "Spirited", "Compassionate", "Radiant", "Resilient",
                "Elegant", "Witty", "Vibrant", "Playful", "Noble", "Zealous", "Gleeful", "Enchanting",
                "Serendipitous", "Flamboyant", "Amiable", "Rugged", "Shimmering", "Coy", "Snazzy",
                "Profound", "Captivating", "Humane", "Breathtaking", "Wondrous", "Rustic", "Enthralling",
                "Graceful", "Boisterous", "Euphoric", "Radiant", "Optimistic", "Silly", "Lyrical",
                "Eccentric", "Audacious", "Dreamy", "Mystical", "Benevolent", "Ornate", "Jubilant",
                "Gritty", "Grandiose", "Jubilant", "Endearing", "Luxurious", "Artistic", "Refined",
                "Snarky", "Compassionate", "Vivacious", "Bubbly", "Quirky", "Resolute", "Nostalgic",
                "Inventive", "Radiant", "Cozy", "Bizarre", "Jubilant", "Charismatic", "Introspective",
                "Perplexing", "Honest", "Wholesome", "Fearless", "Humble", "Eloquent", "Divine"
        ));

        List<String> nouns = new ArrayList<>(Arrays.asList(
                "Star", "Ocean", "Forest", "Desert", "Moon", "Galaxy", "Planet", "Universe",
                "Journey", "Story", "Legend", "Fable", "Kingdom", "Castle", "Village", "Bridge",
                "River", "Waterfall", "Mountain", "Valley", "Canyon", "Hill", "Cliff", "Cave",
                "Cloud", "Rain", "Snow", "Storm", "Sun", "Shadow", "Twilight", "Dawn",
                "Book", "Scroll", "Map", "Ink", "Feather", "Sword", "Shield", "Crown",
                "Heart", "Soul", "Spirit", "Dream", "Wish", "Smile", "Tear", "Laughter",
                "Song", "Melody", "Chorus", "Rhythm", "Echo", "Whisper", "Silence", "Voice",
                "Flower", "Tree", "Leaf", "Petal", "Thorn", "Seed", "Fruit", "Branch",
                "Window", "Door", "Path", "Road", "Trail", "Journey", "Maze", "Labyrinth",
                "Light", "Glow", "Spark", "Flame", "Ember", "Ash", "Smoke", "Fire",
                "Stone", "Rock", "Pebble", "Crystal", "Gem", "Diamond", "Ruby", "Sapphire",
                "Friendship", "Kindness", "Love", "Courage", "Wisdom", "Hope", "Strength", "Adventure"
        ));

        String adjective = GetRandom.stringListItem(adjectives);
        String noun = GetRandom.stringListItem(nouns);
        return String.format("Operation %s %s", adjective, noun);

    }

}
