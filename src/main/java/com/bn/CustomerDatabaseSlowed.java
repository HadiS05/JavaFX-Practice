package com.bn;

import java.util.Map;

// Here we have constructed a "slowed" Database to simulate latency.
// This is the one we will use in production. The original CustomerDatabase
// will be used for testing purposes.
public class CustomerDatabaseSlowed extends CustomerDatabase{
    private final int delay;

    public CustomerDatabaseSlowed(int delay) {
        this.delay = delay;
    }

    @Override
    public int saveCustomer(Map<String, String> customer) {
        delay();
        return super.saveCustomer(customer);
    }

    public void delay(){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
