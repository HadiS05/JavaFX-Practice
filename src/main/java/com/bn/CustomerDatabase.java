package com.bn;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerDatabase {
    // Our "database" with Integer as a key, and the inner map as our account name and numbers
    Map<Integer, Map<String, String>> data = new HashMap<>();
    private Integer nextKey = 0; // Our row index for the database

    public int saveNewCustomer(Map<String, String> customer) throws DuplicateCustomerException {
        // Below we initialize an Optional object of type string, this protects any null cases
        Optional<String> accountNumberOptional = getAccount(customer);
        // Proceed through the logic if the Optional is not empty
        if (accountNumberOptional.isPresent()) {
            // Extract the String accountNumber from Optional
            String accountNumber = accountNumberOptional.get();
            // If the accountNumber isn't already present in the database
            if (!isAccountOnFile(accountNumber)) {
                return saveCustomer(customer); // We can save the customer
            } else {
                throw new DuplicateCustomerException(accountNumber); // Otherwise throw custom exception
            }
        } else {
            // If the Optional is empty, meaning the record doesn't have an account number, then
            // we must inform that an account number is required.
            throw new IllegalArgumentException("Customer record must have an account number");
        }
    }

    public int saveCustomer(Map<String, String> customer) {
        // Add an "id" to the record while keeping track of the key.
        customer.put("_id", (++nextKey).toString()); 
        data.put(nextKey, customer);
        return nextKey;
    }

    public Map<Integer, Map<String, String>> getData(){
        return data;
    }

    public Boolean isAccountOnFile(String accountNumber){
        // Here we get the values of the Map, and convert it to a stream in which we can
        // run a search to find any matching value that is equal to the given accountNumber
        // if not found we can return false (since Optional.map will return null if not found).
        return data.values().stream().anyMatch(record -> getAccount(record).map(value -> value.equals(accountNumber)).orElse(false));
    }

    public Optional<String> getAccount(Map<String,String> customerRecord) {
        // Here we return a Optional String that can either have the account from the record given, or simply be empty
        return Optional.ofNullable(customerRecord.get("account"));
    }
}
