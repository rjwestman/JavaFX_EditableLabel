package com.github.rjwestman.editableLabelExample;

import com.github.rjwestman.editableLabel.EditableLabel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Example1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox root = new VBox(10.0);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.TOP_CENTER);

        EditableLabel editableLabel = new EditableLabel("Test EditableLabel.");
        editableLabel.setEditableClicks(2);
        root.getChildren().add(editableLabel);

        root.getChildren().add(new Button("This is just for changing focus"));

        Button clearEditableLabel = new Button("Reset EditableLabel");
        clearEditableLabel.setOnMouseClicked( event -> editableLabel.setBaseText("Test EditableLabel."));
        root.getChildren().add(clearEditableLabel);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EditableLabel - Example1");
        primaryStage.show();
    }
}
