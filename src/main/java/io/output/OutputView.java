package io.output;

import data.Hotel;
import domain.Customer;
import domain.Reservation;
import domain.Room;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OutputView {

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

    // ------- 준 예약(객실 장바구니에 담기) -------시작
    public void printSelectRoomView(Customer customer, Map<Integer, Room> roomList) {
        System.out.println("--------------------------");
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("예약하실 객실을 선택해 주세요.");

        Iterator<Integer> iter = roomList.keySet().iterator();
        while (iter.hasNext()) {
            Integer key = iter.next();
            int roomNum = roomList.get(key).getNumber();
            String roomType = roomList.get(key).getRoomType().getType();
            long roomCost = roomList.get(key).getCost();
            String roomStatus;
            if (roomList.get(key).isReserved()) {
                roomStatus = "예약완료";
            } else {
                roomStatus = "예약가능";
            }
            System.out.println(
                key + ". " + roomNum + "호 | " + roomType + " | W " + roomCost + " | " + roomStatus);
        }
        System.out.println("0. 취소");
    }

    public void printAlreadyReservedRoomView() {
        System.out.println("--------------------------");
        System.out.println("이미 예약된 방입니다.");
        System.out.println("다른 객실을 선택해주세요.");
    }

    public void printRoomInfo(Room selectRoom) {
        System.out.println("--------------------------");
        System.out.println(
            selectRoom.getNumber() + "호 | " + selectRoom.getRoomType().getType() + " | W"
                + selectRoom.getCost() + " | 이 선택되었습니다.");
        System.out.println("선택하신 객실을 장바구니에 담으시겠습니까?");
        System.out.println("1.확인        2.취소");
    }

    public void printConfirmedCheckedRoomView() {
        System.out.println("--------------------------");
        System.out.println("선택하신 객실이 장바구니로 이동되었습니다.");
        System.out.println("'객실예약대기목록'에서 결제해주세요. :)");
    }

    public void printCancelReserveRoomView() {
        System.out.println("--------------------------");
        System.out.println("취소 되었습니다.");
        System.out.println(); // 줄 띄움용
    }
}
// ------- 준 예약(객실 장바구니에 담기) -------끝

