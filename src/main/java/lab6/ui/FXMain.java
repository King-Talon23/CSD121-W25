package lab6.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.stage.Stage;

import static lab6.ui.Scenes.OpeningCutscene.getOpeningScene;


public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = getOpeningScene();
        primaryStage.setTitle("XCOM 2: Testing");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private Text createTextBorder() {
        Text border = new Text();
        //* make text size 8, font Consolas, bold, white and have a black background
        border.setStyle("-fx-font-size: 8px; -fx-font-family: Consolas;" +
                "-fx-font-weight: bold; -fx-text-fill: white;");
        border.setTextAlignment(TextAlignment.CENTER);

        return border;
    }


    private void setupConveyorBeltEffect(Text text, int borderType) {
        String[][] borderPattern = getBorderType(borderType);

        AtomicInteger row = new AtomicInteger(0);
        AtomicInteger rowSection = new AtomicInteger(0);

        text.setWrappingWidth(75);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.15), event -> {
                    String currentText = text.getText();
                    String[] lines = currentText.split("\n");

                    if (lines.length > 40) {
                        String updatedText = currentText.replaceFirst(".*\n", "");
                        text.setText(updatedText);
                    }

                    String newText = text.getText() + centerText(borderPattern[row.get()][rowSection.get()]) + "\n";
                    text.setText(newText);

                    rowSection.getAndIncrement();
                    if (rowSection.get() >= borderPattern[row.get()].length) {
                        rowSection.set(0);
                        row.getAndIncrement();
                        if (row.get() >= borderPattern.length) {
                            row.set(0);
                        }
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private static String[][] getBorderType(int borderType) {
        String[][] borderPattern;
        switch (borderType) {
            case 1 -> {
                borderPattern = new String[][]{ // regular
                        {"//\\/\\\\", "//\\  /\\\\", "<|(> () <)|>", "\\\\/  \\//", "\\\\/\\//"},
                        {"/  \\", "/ /\\ \\", "\\ \\/ /", "\\  /", "//\\\\"},
                        {"/  \\", "/ /\\ \\", "(  ()  )", "\\ \\/ /", "\\  /"},
                        {"\\\\//", "/  \\", "/ /\\ \\", " \\ \\/ /", "\\  /"}
                };
            }
            case 2 -> {
                borderPattern = new String[][]{ // winner
                        {"\\    /", " \\  / ", "  \\/  ", "  /\\  ", " /  \\ "},  // W
                        {"\\    /", " \\  / ", "  \\/  ", "  /\\  ", " /  \\ "},  // I
                        {"\\\\  //", " \\/  \\", "  /\\  ", " /  \\ ", "////\\\\ "},  // N
                        {"//  \\\\", "  \\/  ", "  /\\  ", " /  \\ ", "////\\\\ "},  // N
                        {"\\    /", " \\  / ", "  \\/  ", "  /\\  ", " /  \\ "},  // E
                        {"\\\\  //", " \\/  \\", "  /\\  ", " /  \\ ", "////\\\\ "},  // R
                };
            }
            case 3 -> {
                borderPattern = new String[][]{ // loser
                        {"\\    /", " \\  / ", "  \\/  ", "  /\\  ", " /  \\ "},  // L
                        {"\\\\  //", " \\/  \\", "  /\\  ", " /  \\ ", "////\\\\ "},  // O
                        {"\\\\  //", " \\/  \\", "  /\\  ", " /  \\ ", "////\\\\ "},  // S
                        {"\\\\  //", " \\/  \\", "  /\\  ", " /  \\ ", "////\\\\ "},  // E
                        {"\\\\  //", " \\/  \\", "  /\\  ", " /  \\ ", "////\\\\ "},  // R
                };
            }
            default -> {
                borderPattern = new String[][]{ // ERROR
                        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
                        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
                        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
                        {"ERROR", "ERROR", "ERROR", " ERROR", "ERROR"}
                };
            }

        }
        return borderPattern;
    }

    private String centerText(String text) {
        // center the given String
        int maxWidth = 14;
        int padding = (maxWidth - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();

        // Add spaces to center the text
        centeredText.append(" ".repeat(Math.max(0, padding)));
        centeredText.append(text);

        // fill the rest of the space to match the maxWidth
        while (centeredText.length() < maxWidth) {
            centeredText.append(" ");
        }

        return centeredText.toString();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
