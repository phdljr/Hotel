package io.output;

import data.Hotel;
import domain.Customer;
import domain.Reservation;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdminOutput {

    public void printReserveAllRoom(Customer customer, List<Reservation> reservations) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("모든 예약 내역을 조회합니다.");
        System.out.println();
        System.out.println("[ 호텔 총 수입 ]");
        System.out.printf("- W %d\n", Hotel.getMoney());
        System.out.println();
        System.out.println("[ 객실 예약 목록 ]");
        if (reservations.isEmpty()) {
            System.out.println("- 예약된 객실이 없습니다.");
        } else {
            for (Reservation reservation : reservations) {
                int number = reservation.getRoom().getNumber();
                String uuid = reservation.getUuid();
                String customerId = reservation.getCustomer().getId();
                String dateTime = reservation.getDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                System.out.printf("- %d호 | %s | %s | %s\n", number, uuid, customerId, dateTime);
            }
        }
        System.out.println();
        System.out.println("1. 돌아가기");
    }
}
