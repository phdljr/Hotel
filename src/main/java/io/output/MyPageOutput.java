package io.output;

import domain.Customer;
import domain.Reservation;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 마이페이지 화면
 */
public class MyPageOutput {

    public void printMyPageView(Customer customer, Map<String, Reservation> reservationMap) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println();
        System.out.printf("ID: %s\n", customer.getId());
        System.out.printf("이름: %s\n", customer.getName());
        System.out.printf("전화번호: %s\n", customer.getPhoneNumber());
        System.out.printf("소지금: %d\n", customer.getMoney());
        System.out.println();
        System.out.println("[ 객실 예약 목록 ]");
        System.out.println();
        if (reservationMap.isEmpty()) {
            System.out.println("- 예약한 객실이 없습니다.");
        } else {
            for (Reservation reservation : reservationMap.values()) {
                int number = reservation.getRoom().getNumber();
                long cost = reservation.getRoom().getCost();
                String uuid = reservation.getUuid();
                String dateTime = reservation.getDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String roomType = reservation.getRoom().getRoomType().getType();
                System.out.printf("- %d호 | %s | W %d | %s | %s\n", number, roomType, cost, dateTime,
                    uuid);
            }
        }
        System.out.println();
        System.out.println("1. 돌아가기");
    }
}
