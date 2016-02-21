package com.github.rjwestman.editableLabelExample;

import com.github.rjwestman.editableLabel.EditableLabel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Example2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox root = new VBox(10.0);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.TOP_CENTER);

        EditableLabel editableLabel = new EditableLabel("Test EditableLabel.");
        root.getChildren().add(editableLabel);

        Label label = new Label("The following TextField is bound bidirectional to our EditableLabel");
        label.setWrapText(true);
        root.getChildren().add(label);

        TextField textField = new TextField("Bidirectionally bound");
        textField.textProperty().bindBidirectional(editableLabel.baseTextProperty());
        root.getChildren().add(textField);

        root.getChildren().add(new Button("This is just to change focus."));


        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EditableLabel - Example2");
        primaryStage.show();
    }
}
