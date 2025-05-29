package com.bn;

import java.util.Map;

public class CustomerDAO {
    // Static since we only want one database
    private static CustomerDatabase database = new CustomerDatabase();

    public int saveCustomer(Map<String, String> customerRecord){
        return database.saveCustomer(customerRecord);
    }
}
