package data;

import domain.Customer;
import domain.CustomerType;
import java.util.HashMap;
import java.util.Map;

public class CustomerDatabase {

    private static final CustomerDatabase customerDatabase = new CustomerDatabase();
    private final Map<String, Customer> customers = new HashMap<>();

    private CustomerDatabase() {
        customers.put("admin", new Customer("admin", "관리자", "", 0, CustomerType.ADMIN));
        customers.put("test1",
            new Customer("test1", "이종렬", "010-1234-5678", 1000000000L, CustomerType.CUSTOMER));
        customers.put("test2",
            new Customer("test2", "김한신", "011-1234-5678", 2000000000L, CustomerType.CUSTOMER));
        customers.put("test3",
            new Customer("test3", "이예진", "012-1234-5678", 3000000000L, CustomerType.CUSTOMER));
        customers.put("test4",
            new Customer("test4", "강용수", "013-1234-5678", 4000000000L, CustomerType.CUSTOMER));
    }

    public static CustomerDatabase getCustomerDatabase() {
        return customerDatabase;
    }

    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer findById(String id) {
        return customers.get(id);
    }
}
