package service;

import data.CustomerDatabase;
import domain.Customer;
import domain.CustomerType;

import java.util.Objects;

public class CustomerService {
    private final CustomerDatabase customerDatabase = new CustomerDatabase();

    public Customer login(String id){
        return findById(id);
    }

    public Customer findById(String id){
        Customer customer = customerDatabase.findById(id);
        if(Objects.isNull(customer)){
           throw new IllegalArgumentException();
        }

        return customer;
    }

    public boolean isAdmin(Customer customer){
        return customer.getCustomerType() == CustomerType.ADMIN;
    }
}
