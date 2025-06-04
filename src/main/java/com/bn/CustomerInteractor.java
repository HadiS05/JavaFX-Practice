package com.bn;

import javafx.beans.binding.Bindings;

public class CustomerInteractor {
    private final CustomerModel model;
    private final CustomerBroker broker = new CustomerBroker();

    public CustomerInteractor(CustomerModel model){
        this.model = model;
        // Below we are binding the model's safe-saving boolean property to the two string properties.
        // Thus whenever the two string properties sense a change, they will trigger the validData() function
        model.getSafeToProperty().bind(Bindings.createBooleanBinding(this::validData, model.getAccountNameProperty(), model.getAccountNumberProperty()));
    }

    public Boolean save() {
        String name = model.getAccountName(); // Save the name beforehand to avoid any changes in live State
        String accountNum = model.getAccountNumber(); // Same as above for accountNumber
        try {
            int result = broker.saveCustomer(customerFromModel());
            System.out.println("Saving account of " + name + ", # is " + 
            accountNum + " Final result: " + result);
            return true;
        } catch (DuplicateCustomerException e) {
            return false;
        }
    }

    public Customer customerFromModel() {
        Customer customer = new Customer();
        customer.setName(model.getAccountName());
        customer.setAccount(model.getAccountNumber());
        return customer;
    }

    public Boolean validData() {
        // Return true if model accountName and Number are not empty.
        return !model.getAccountName().isEmpty() && !model.getAccountNumber().isEmpty();
    }
}
