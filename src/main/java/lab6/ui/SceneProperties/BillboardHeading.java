package lab6.ui.SceneProperties;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;

public class BillboardHeading {
    public static TextFlow heading(String inputText) {
        TextFlow textFlow = new TextFlow();
        textFlow.setTextAlignment(TextAlignment.CENTER);

        styleBorderLines(textFlow); // top border

        Text inputTextNode = new Text(inputText + "\n");
        inputTextNode.setStyle("-fx-font-weight: bold; -fx-fill: white; -fx-font-size: 20;");
        textFlow.getChildren().add(inputTextNode);

        styleBorderLines(textFlow); // bottom border

        return textFlow;
    }


    private static void styleBorderLines(TextFlow textFlow) {
        int lineLength = 103; // this assumes full screen
        String line = "=".repeat(lineLength) + "\n";

        Text textNode = new Text(line);
        textNode.setStyle("-fx-fill: lightgreen; -fx-font-size: 20;");
        textFlow.getChildren().add(textNode);
    }
}
