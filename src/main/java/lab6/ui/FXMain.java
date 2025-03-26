package lab6.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import javax.print.DocFlavor;

public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();

        // video stuff
        URL videoPath = FXMain.class.getResource("/xcom_opening_final.mp4");
        String stringVideoPath = videoPath.toExternalForm();
        Media media = new Media(stringVideoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        // textArea propertys
        TextArea leftTextArea = createTextArea();
        TextArea rightTextArea = createTextArea();
        leftTextArea.setStyle("-fx-font-size: 12px; -fx-font-family: Arial;");
        rightTextArea.setStyle("-fx-font-size: 12px; -fx-font-family: Arial;");
        setupConveyorBeltEffect(leftTextArea);
        setupConveyorBeltEffect(rightTextArea);

        borderPane.setCenter(mediaView);
        borderPane.setLeft(leftTextArea);
        borderPane.setRight(rightTextArea);

        BorderPane.setMargin(leftTextArea, new Insets(10));
        BorderPane.setMargin(rightTextArea, new Insets(10));


        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("XCOM 2: Testing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setStyle("-fx-font-size: 20px; -fx-background-color: lightgray;");
        textArea.setPrefWidth(200);
        textArea.setPrefHeight(600);
        return textArea;
    }

    private void setupConveyorBeltEffect(TextArea textArea) {
        final int[] i = {0};
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    String currentText = textArea.getText();
                    if (currentText.split("\n").length > 20) {// max lines in feild
                        String updatedText = currentText.replaceFirst(".*\n", "");
                        textArea.setText(updatedText);
                    }


                    textArea.appendText(i[0] + "New line of text\n");
                    i[0]++;
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
