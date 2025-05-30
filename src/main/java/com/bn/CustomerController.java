package com.bn;

import javafx.concurrent.Task;
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
        Task<Void> saveTask = new Task<Void>() {
            @Override
            protected Void call() {
                interactor.save();
                return null;
            }
        };
        // When the save task is completed successfully, then run the post-task actions
        saveTask.setOnSucceeded(evt -> postTaskGUI.run());
        Thread saveThread = new Thread(saveTask);
        saveThread.start();
    }

    public Region getView() {
        return viewBuilder.build(); // Get the view from the builder.
    }
}