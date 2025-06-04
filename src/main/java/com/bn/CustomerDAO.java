package com.bn;

import java.util.Map;

public class CustomerDAO {
    // Static since we only want one database
    private static CustomerDatabaseSlowed database = new CustomerDatabaseSlowed(3000);

    public int saveCustomer(Map<String, String> customerRecord) throws DuplicateCustomerException{
        return database.saveNewCustomer(customerRecord);
    }
}
