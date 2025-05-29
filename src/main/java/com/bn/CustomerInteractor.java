package com.bn;

public class CustomerInteractor {
    private final CustomerModel model;
    private final CustomerBroker broker = new CustomerBroker();

    public CustomerInteractor(CustomerModel model){
        this.model = model;
    }

    public void save() {
        int result = broker.saveCustomer(customerFromModel());
        System.out.println("Saving account of " + model.getAccountName() + ", # is " + 
            model.getAccountNumber() + " Final result: " + result);
    }

    public Customer customerFromModel() {
        Customer customer = new Customer();
        customer.setName(model.getAccountName());
        customer.setAccount(model.getAccountNumber());
        return customer;
    }
}
