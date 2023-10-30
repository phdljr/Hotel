package io.output;

import domain.Room;
import java.util.List;

public class BasketOutput {

    public void printBasketTitle(String customerName) { // 장바구니 제목 문구
        System.out.println("[ 뇌정지 호텔 - " + customerName + "님 ]");
    }

    public void printBasketText() {
        System.out.println("선택하신 객실을 확인해주세요.");
        System.out.println();
    }

    public void haveEmptyBasket() {
        System.out.println("선택하신 객실이 없습니다.");
        System.out.println();
    }

    public void checkBasketMenu() { // 장바구니 메뉴
        System.out.println("1. 예약 진행");
        System.out.println("2. 예약 제외");
        System.out.println("3. 돌아가기");
    }

    public void printBasketList(List<Room> basket) { // 장바구니 목록 출력
        for (int i = 0; i < basket.size(); i++) {
            Room room = basket.get(i);
            System.out.printf(
                "%d. %s | %d | W %d\n",
                i + 1, room.getRoomType().getType(), room.getNumber(),
                room.getCost());
        }
        System.out.println();
    }

    public void printBasketPrice(long price) { // 총금액 출력
        System.out.println("[ 총 금액 ]");
        System.out.println();
        System.out.println("W " + price);
        System.out.println();
    }

    public void reserveBasket() { // 1. 예약 진행 문구
        System.out.println("예약을 진행하시겠습니까?");
        System.out.println();
        System.out.println("1. 확인\t2. 취소");
    }

    public void deleteBasket() { // 2. 예약 제외 문구
        System.out.println("예약을 제외할 객실을 선택해주세요.");
        System.out.println();
    }

    public void confirmDeleteBasket(Room room) { // 2. 예약 제외 확인  문구
        System.out.println("해당 객실을 예약 목록에서 제외하시겠습니까?");
        System.out.println();
        System.out.println(
            "\" " + room.getNumber() + " | " + room.getRoomType().getType() + " | W "
                + room.getCost()
                + "\" ");
        System.out.println();
        System.out.println("1. 확인\t2. 취소");
    }

    public void successReserveBasket() { // 1. 예약 성공 문구
        System.out.println("예약이 성공적으로 진행되었습니다.");
        System.out.println();
    }

    public void failureReserveBasket() { // 1. 예약 실패 문구(소지금 부족)
        System.out.println("금액이 부족하여 예약에 실패하였습니다.");
        System.out.println();
    }

    public void successDeleteBasket() { // 2. 예약 제외 완료 문구
        System.out.println("해당 객실이 예약 목록에서 제외되었습니다.");
        System.out.println();
    }

}
