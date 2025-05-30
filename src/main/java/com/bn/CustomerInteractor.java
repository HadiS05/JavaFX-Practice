package com.bn;

public class CustomerInteractor {
    private final CustomerModel model;
    private final CustomerBroker broker = new CustomerBroker();

    public CustomerInteractor(CustomerModel model){
        this.model = model;
    }

    public void save() {
        String name = model.getAccountName(); // Save the name beforehand to avoid any changes in live State
        String accountNum = model.getAccountNumber(); // Same as above for accountNumber
        int result = broker.saveCustomer(customerFromModel());
        System.out.println("Saving account of " + name + ", # is " + 
            accountNum + " Final result: " + result);
    }

    public Customer customerFromModel() {
        Customer customer = new Customer();
        customer.setName(model.getAccountName());
        customer.setAccount(model.getAccountNumber());
        return customer;
    }
}
