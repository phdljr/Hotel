package io.output;

import domain.Customer;
import domain.Room;
import java.util.Iterator;
import java.util.Map;

public class RoomCheckOutput {

    // ------- 준 예약(객실 장바구니에 담기) -------시작
    public void printSelectRoomView(Customer customer, Map<Integer, Room> roomList) {
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
        System.out.println("이미 예약된 방입니다.");
        System.out.println("다른 객실을 선택해주세요.");
    }

    public void printRoomInfo(Room selectRoom) {
        System.out.println(
            selectRoom.getNumber() + "호 | " + selectRoom.getRoomType().getType() + " | W"
                + selectRoom.getCost() + " | 이 선택되었습니다.");
        System.out.println("선택하신 객실을 장바구니에 담으시겠습니까?");
        System.out.println("1.확인        2.취소");
    }

    public void printConfirmedCheckedRoomView() {
        System.out.println("선택하신 객실이 장바구니로 이동되었습니다.");
        System.out.println("'객실예약대기목록'에서 결제해주세요. :)");
    }

    public void printCancelReserveRoomView() {
        System.out.println("취소 되었습니다.");
        System.out.println(); // 줄 띄움용
    }
    // ------- 준 예약(객실 장바구니에 담기) -------끝

}
