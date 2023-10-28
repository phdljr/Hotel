package io.output;

import domain.Customer;
import domain.Reservation;
import domain.Room;
import domain.RoomType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printInitView() {
        System.out.println("[ 뇌정지 호텔 ]");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
    }

    public void printLoginView() {
        System.out.println("[ 뇌정지 호텔 ]");
        System.out.println("로그인 정보를 입력해 주세요.");
        System.out.println();
        System.out.print("ID: ");
    }

    public void printWrongInputId() {
        System.out.println("로그인 정보가 잘못되었습니다. 다시 입력해 주세요.");
        System.out.println();
    }

    public void printAlreadyExistId() {
        System.out.println("이미 존재하는 ID입니다. 다른 ID를 입력해 주세요.");
        System.out.println();
    }

    public void printSignUpTitleView() {
        System.out.println("[ 뇌정지 호텔 ]");
        System.out.println("회원가입 정보를 입력해 주세요.");
        System.out.println("전화번호를 다음 형식과 같이 입력해 주세요. (XXX-XXXX-XXXX)");
        System.out.println();
    }

    public void printSignUpView(int step) {
        switch (step) {
            case 1:
                System.out.print("ID: ");
                break;
            case 2:
                System.out.print("이름: ");
                break;
            case 3:
                System.out.print("전화번호: ");
                break;
            case 4:
                System.out.print("소지금: ");
                break;
        }
    }

    public void printMyPageView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println();
        System.out.printf("ID: %s\n", customer.getId());
        System.out.printf("이름: %s\n", customer.getName());
        System.out.printf("전화번호: %s\n", customer.getPhoneNumber());
        System.out.printf("소지금: %d\n", customer.getMoney());
        System.out.println();
        System.out.println("객실 예약 목록");
        /* TODO
            객실 예약 목록 출력
         */
        System.out.println();
        System.out.println("1. 돌아가기");
    }

    public void printCustomerMainView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("서비스를 선택해 주세요.");
        System.out.println();
        System.out.println("1. 객실 예약");
        System.out.println("2. 객실 예약 취소");
        System.out.println("3. 객실 예약 대기 목록");
        System.out.println("4. 마이 페이지");
        System.out.println("5. 로그아웃");
    }

    public void printAdminMainView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("서비스를 선택해 주세요.");
        System.out.println();
        System.out.println("1. 모든 예약 목록 조회");
        System.out.println("2. 로그아웃");
    }

    public void printReserveAllRoom(Customer customer, List<Reservation> reservations) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("현재 예약된 모든 객실을 조회합니다.");
        System.out.println();
        if (reservations.isEmpty()) {
            System.out.println("- 예약된 객실이 없습니다.");
        } else {
            for (Reservation reservation : reservations) {
                int number = reservation.getRoom().getNumber();
                String uuid = reservation.getUuid();
                String customerId = reservation.getCustomer().getId();
                System.out.printf("- %d호 | W %s | %s\n", number, uuid, customerId);
            }
        }
        System.out.println();
        System.out.println("1. 돌아가기");
    }

    public void printLogoutView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("로그아웃을 하시겠습니까?");
        System.out.println();
        System.out.println("1. 확인\t\t2. 취소");
    }

    public void printLogoutCommentView() {
        System.out.println("로그아웃이 정상적으로 처리되었습니다.");
        System.out.println();
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
            RoomType roomType = roomList.get(key).getRoomType();
            long roomCost = roomList.get(key).getCost();
            String roomStatus;
            if (roomList.get(key).isReserved() == true) {
                roomStatus = "예약완료";
            } else {
                roomStatus = "예약가능";
            }
            System.out.println(
                key + ". " + roomNum + "호 | " + roomType + " | W" + roomCost + " | " + roomStatus);
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
            selectRoom.getNumber() + "호 | " + selectRoom.getRoomType() + " | W"
                + selectRoom.getCost() + " | 이 선택되었습니다.");
        System.out.println("선택하신 객실을 장바구니에 담으시겠습니까?");
        System.out.println("1.확인        2.취소");
    }

    public void printConfirmedCheckedRoomView() {
        System.out.println("--------------------------");
        System.out.println("선택하신 객실이 장바구니로 이동되었습니다.");
        System.out.println("'객실예약대기목록'에서 결제해주세요. :)");
        System.out.println(); // 줄 띄움용
    }

    public void printCancelReserveRoomView() {
        System.out.println("--------------------------");
        System.out.println("취소 되었습니다.");
        System.out.println(); // 줄 띄움용
    }
}
// ------- 준 예약(객실 장바구니에 담기) -------끝