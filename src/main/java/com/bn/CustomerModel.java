package com.bn;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerModel {
    // We make these final so that there is no change to the reference of these
    // variables. They will always exist. But we can change their values using
    // getters and setters.
    private final StringProperty accountNumber = new SimpleStringProperty("");
    private final StringProperty accountName = new SimpleStringProperty("");

    public CustomerModel(){

    }

    // Getter for accountNumber
    public String getAccountNumber() {
        return accountNumber.get();
    }

    // Set accountNumber
    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
    }

    // Get the reference for accountNumber
    public StringProperty getAccountNumberProperty(){
        return accountNumber;
    }

    // Getter for accountName
    public String getAccountName() {
        return accountName.get();
    }

    // Setter for accountName
    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }

    // Get the reference for accountName
    public StringProperty getAccountNameProperty(){
        return accountName;
    }
}
