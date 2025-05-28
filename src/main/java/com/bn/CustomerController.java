package com.bn;

import javafx.scene.layout.Region;
import javafx.util.Builder;

public class CustomerController {
    private Builder<Region> viewBuilder; // This is our builder for the view (GUI)
    private CustomerInteractor interactor; // Here we will have all the eventhandlers for buttons, etc.

    public CustomerController(){
        CustomerModel model = new CustomerModel(); // This is where we save our model's data
        interactor = new CustomerInteractor(model); // Instantiate a new interactor
        viewBuilder = new CustomerViewBuilder(model, interactor::save); // Instantiate a new view
    }
    public Region getView() {
        return viewBuilder.build(); // Get the view from the builder.
    }
}