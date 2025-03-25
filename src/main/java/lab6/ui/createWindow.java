package lab6.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class createWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        window.setTitle("Hello World!");
        Button button = new Button();
        button.setText("press me");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.print("Hello World!");
            }

        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        window.setScene(new Scene(root, 300, 250));
        window.show();
    }
}
