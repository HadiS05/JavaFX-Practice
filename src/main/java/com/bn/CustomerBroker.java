package com.bn;

import java.util.HashMap;
import java.util.Map;

public class CustomerBroker {
    private final CustomerDAO dao = new CustomerDAO();

    public int saveCustomer(Customer customer) throws DuplicateCustomerException {
        return dao.saveCustomer(customerToRecord(customer));
    }

    public Map<String,String> customerToRecord(Customer customer) {
        Map<String,String> customerRecord = new HashMap<>();
        customerRecord.put("name", customer.getName());
        customerRecord.put("account", customer.getAccount());
        return customerRecord;
    }
}
