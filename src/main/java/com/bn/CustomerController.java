package com.bn;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.util.Builder;

// The Controller takes care of any threads
public class CustomerController {
    private Builder<Region> viewBuilder; // This is our builder for the view (GUI)
    private CustomerInteractor interactor; // Here we will have all the eventhandlers for buttons, etc.

    public CustomerController(){
        CustomerModel model = new CustomerModel(); // This is where we save our model's data
        interactor = new CustomerInteractor(model); // Instantiate a new interactor
        viewBuilder = new CustomerViewBuilder(model, this::saveCustomer); // Instantiate a new view
    }

    public void saveCustomer(Runnable postTaskGUI) {
        // We use a Task instead of a Thread here because a task will notify us
        // when it has completed, allowing us to have more control over what
        // we can do.
        Task<Boolean> saveTask = new Task<>() {
            @Override
            protected Boolean call() {
                return interactor.save();
            }
        };
        // When the save task is completed successfully, then run the post-task actions
        saveTask.setOnSucceeded(evt -> {
            postTaskGUI.run();
            if (!saveTask.getValue()) { // If the task was not successfully saved
                // Then call an alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("That account number already exists.");
                alert.show();
            } else {
                // If our task is successful, give feedback to the user.
                Alert success = new Alert(Alert.AlertType.CONFIRMATION);
                success.setContentText("Account saved.");
                success.show();
            }
        });
        Thread saveThread = new Thread(saveTask);
        saveThread.start();
    }

    public Region getView() {
        return viewBuilder.build(); // Get the view from the builder.
    }
}