package com.bn;

public class CustomerInteractor {
    private final CustomerModel model;

    public CustomerInteractor(CustomerModel model){
        this.model = model;
    }

    public void save() {
        System.out.println("Welcome " + model.getAccountName() + " your # is " + model.getAccountNumber());
    }
}
