package com.bn;

public class DuplicateCustomerException extends Exception {
    public DuplicateCustomerException(String accountNumber) {
        // Create a custom exception for a duplicate customer account number
        super("Account " + accountNumber + " already on file.");
    }
}
