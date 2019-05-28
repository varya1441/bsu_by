package com.tihonova.observer;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    Current<String> current = new Current<>();

    @Override
    public void start(Stage stage) {
        LogTextArea area = new LogTextArea();
        area.setEditable(false);
        PreviewLabel label = new PreviewLabel();
        label.setFont(Font.font(50));
        label.setAlignment(Pos.CENTER);


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(area, 0, 0);
        gridPane.add(label, 0, 2);

        Scene scene = new Scene(gridPane, 500, 500);

        current.registerObserver(label);
        current.registerObserver(area);
        keyPressed(scene);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void keyPressed(Scene scene) {
        scene.setOnKeyPressed((e) -> {
            String str;
            if (e.getText().isEmpty()) {
                str = e.getCode().getName();
            }
            else {
                str = e.getText();
            }
            current.notifyObservers(str);
        });
    }


}