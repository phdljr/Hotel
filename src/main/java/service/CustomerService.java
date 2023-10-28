package service;

import data.CustomerDatabase;
import domain.Customer;
import domain.CustomerType;
import java.util.Objects;

public class CustomerService {

    private final CustomerDatabase customerDatabase = CustomerDatabase.getCustomerDatabase();

    public boolean contains(String id) {
        Customer customer = findById(id);
        return !Objects.isNull(customer);
    }

    public Customer login(String id) {
        return findById(id);
    }

    public Customer findById(String id) {
        return customerDatabase.findById(id);
    }

    public Customer signUp(String id, String name, String phoneNumber, long money) {
        Customer customer = new Customer(id, name, phoneNumber, money, CustomerType.CUSTOMER);
        customerDatabase.save(customer);
        return customer;
    }

    public boolean isAdmin(Customer customer) {
        return customer.getCustomerType() == CustomerType.ADMIN;
    }
}
