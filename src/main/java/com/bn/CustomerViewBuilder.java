package com.bn;

import java.util.Objects;
import java.util.function.Consumer;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

// In ViewBuilder we will be making our whole GUI.
// build() will only have the root node's construction

// We will have a model to display certain data
// We will have a thread called handler to wait for any events from buttons

public class CustomerViewBuilder implements Builder {
    private final CustomerModel model;
    private final Consumer<Runnable> handler; 
    // We are using a consumer so that we can run the thread and then "consume" it (meaning return nothing)

    public CustomerViewBuilder(CustomerModel model, Consumer<Runnable> handler){
        this.model = model;
        this.handler = handler; // This is meant to run a method after pressing button
    }

    @Override
    public Region build(){
        // BorderPane is a layout that has Top, Left, Right, Bottom, and Center containers.
        BorderPane root = new BorderPane();
        // requireNonNull ensures that our object (in this case a css file) exists. Otherwise throw an exception
        root.getStylesheets().add(Objects.requireNonNull(
            this.getClass().getResource("stylesheet.css").toExternalForm()
        ));
        root.setTop(createHeading("Account Login"));
        root.setBottom(createBottom());
        root.setCenter(createCenter());
        return root;
    }

    public Node createHeading(String content) {
        Label lbl = new Label(content);
        lbl.getStyleClass().add("heading-label");
        return lbl;
    }
    public Node createCenter() {
        VBox result = new VBox(6, // padding in between children
            accountBox(), nameBox());
        result.setPadding(new Insets(20));
        return result;
    }

    public Node accountBox() {
        return new HBox(6, createPromptLabel("Account #:"), boundTextField(model.getAccountNumberProperty()));
    }

    public Node nameBox() {
        return new HBox(6, createPromptLabel("Name:"), boundTextField(model.getAccountNameProperty()));
    }

    public Node createBottom() {
        Button save = new Button("Save");
        save.setOnAction(evt -> {
            save.setDisable(true); // Initially disable the button after it's pressed (to avoid double saves)
            // Consumer performs the runnable task (enables the button)
            handler.accept(() -> save.setDisable(false));
        }); // Run the function
        HBox result = new HBox(10, save);
        result.setAlignment(Pos.CENTER_RIGHT);
        return result;
    }

    public Node createPromptLabel(String content) {
        return createStyledLabel(content, "prompt-label");
    }

    public Node boundTextField(StringProperty boundProperty) {
        TextField txt = new TextField();
        txt.textProperty().bindBidirectional(boundProperty);
        return txt;
    }
    public Node createStyledLabel(String content, String styleClass) {
        Label lbl = new Label(content);
        lbl.getStyleClass().add(styleClass);
        return lbl;
    }
}
