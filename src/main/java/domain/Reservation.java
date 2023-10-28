package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {

    private String uuid;
    private Room room;
    private Customer customer;
    private LocalDateTime dateTime;

    public Reservation(String uuid, Room room, Customer customer, LocalDateTime dateTime) {
        this.uuid = uuid;
        this.room = room;
        this.customer = customer;
        this.dateTime = dateTime;
    }

    public String getUuid() {
        return uuid;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isPassed() {
        LocalDate nowDate = LocalDate.now();
        LocalDate dateOfReservation = dateTime.toLocalDate();
        return dateOfReservation.isBefore(nowDate);
    }
}
