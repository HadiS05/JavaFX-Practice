package com.bn;

import java.util.HashMap;
import java.util.Map;

public class CustomerDatabase {
    // Our "database" with Integer as a key, and the inner map as our account name and numbers
    Map<Integer, Map<String, String>> data = new HashMap<>();
    private Integer nextKey = 0; // Our row index for the database

    public int saveCustomer(Map<String, String> customer){
        customer.put("_id", (++nextKey).toString());
        data.put(nextKey, customer);
        return nextKey;
    }

    public Map<Integer, Map<String, String>> getData(){
        return data;
    }
}
