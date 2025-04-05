package lab6.ui.SceneProperties;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class ScrollBorder {

    public static Text scrollBorder(BorderType borderType) {
        Text textBorder = createTextBorder();
        setupConveyorBeltEffect(textBorder, borderType);
        return textBorder;
    }

    private static Text createTextBorder() {
        Text border = new Text();
        // make text size 8, font Consolas, bold, white and have a black background
        border.setStyle("-fx-font-size: 8px; -fx-font-family: Consolas;" +
                "-fx-font-weight: Bold;-fx-background-color: Black");
        border.setTextAlignment(TextAlignment.CENTER);
        border.setFill(Color.WHITE);
        return border;
    }


    private static void setupConveyorBeltEffect(Text text, BorderType borderType) {
        String[][] borderPattern = getBorderType(borderType);

        AtomicInteger row = new AtomicInteger(0);
        AtomicInteger rowSection = new AtomicInteger(0);

        text.setWrappingWidth(100);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.03333), _ -> {
                    String currentText = text.getText();
                    String[] lines = currentText.split("\n");

                    if (lines.length > 79) { // this assumes full screen
                        String updatedText = currentText.substring(0, currentText.lastIndexOf('\n'));
                        text.setText(updatedText);
                    }

                    String newText = centerText(borderPattern[row.get()][rowSection.get()]) + "\n" + text.getText();
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

    public enum BorderType {
        XCOM, ALIEN
    }

    private static String[][] getBorderType(BorderType borderType) {
        return switch (borderType) {
            /** used this website to complete all borders except regular
             // https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20 - font = big money ne
             **/
            case XCOM -> new String[][]{ // Player turn
                    {"__/     |__/", "| $$ \\/  | $$", "| $$\\  $ | $$", "| $$  $$$| $$", "| $$ $$/$$ $$",
                            "| $$$$  /$$$$", "| $$$    /$$$", " /$$      /$$"}, // M
                    {"\\______/", "|  $$$$$$/", "| $$  | $$", "| $$  | $$", "| $$  | $$", "| $$  \\ $$", " /$$__  $$", " /$$$$$$",},  // O
                    {"\\______/", "|  $$$$$$/", "| $$    $$", "| $$     ", "| $$     ", "| $$  \\__/", " /$$__  $$", "  /$$$$$$"},  // C
                    {"|__/  |__/", "| $$  \\ $$", " /$$/\\  $$", "$$  $$", " \\  $$$$/,",
                            "|  $$/ $$/", "| $$  / $$", " /$$   /$$", "=============="}  // X
            };
            case ALIEN -> new String[][]{ // Alien turn
                    {" \\______/ ", "|  $$$$$$/", " /$$  \\ $$", " \\____  $$", "|  $$$$$$ ", "| $$  \\__/", " /$$__  $$", "  /$$$$$$ "},  // S
                    {"|__/  \\__/", "| $$ \\  $$", "| $$\\  $$$", "| $$  $$$$", "| $$ $$ $$", "| $$$$| $$", "| $$$ | $$", " /$$   /$$"},  // N
                    {"|______/  ", " /$$$$$$  ", "  | $$    ", "  | $$    ", "  | $$    ", "  | $$    ", "|_  $$_/  ", " /$$$$$$  "},  // I
                    {"|________/", "| $$$$$$$$", "| $$      ", "| $$__/   ", "| $$$$$   ", "| $$      ", "| $$_____/", " /$$$$$$$$"},  // E
                    {"|________/", "| $$$$$$$$", "| $$      ", "| $$      ", "| $$      ", "| $$      ", "| $$      ", " /$$      "},  // L
                    {"|__/  |__/", "| $$  | $$", "| $$  | $$", "| $$__  $$", "| $$$$$$$$", "| $$  \\ $$", " /$$__  $$", "  /$$$$$$ ", "=============="}  // A
            };
        };
    }

    private static String centerText(String text) {
        // center the given String
        int maxWidth = 18;
        int padding = (maxWidth - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();

        // Add spaces to center the text
        centeredText.append(" ".repeat(Math.max(0, padding)));
        centeredText.append(text);

        // fills the rest of the space to match the maxWidth
        while (centeredText.length() < maxWidth) {
            centeredText.append(" ");
        }

        return centeredText.toString();
    }
}
