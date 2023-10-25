package domain;

public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private long money;
    private CustomerType role;

    public Customer(String id, String name, String phoneNumber, long money, CustomerType role) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getMoney() {
        return money;
    }

    public CustomerType getCustomerType() {
        return role;
    }
}
