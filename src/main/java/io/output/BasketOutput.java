package io.output;

import domain.Customer;
import domain.Room;
import data.BasketDatabase;
import service.BasketService;

import java.util.List;

public class BasketOutput {
    private BasketDatabase basketDatabase;
    private BasketService basketService;
    private Customer customer;
    public BasketOutput(Customer customer, BasketDatabase basketDatabase, BasketService basketService){
        this.customer = customer;
        this.basketDatabase = basketDatabase;
        this.basketService = basketService;
    }

    public void printBasketTitle(){
        System.out.println("[ 뇌정지 호텔 - " + customer.getName() + "님 ]");
    }

    public void showBasketList(){
        List<Room> basket = basketDatabase.getBasket();
        for(int i=0; i<basket.size();i++){
            Room room = basket.get(i);
            System.out.printf("%d. %s | %s | W %d", i+1, room.getRoomName(), room.getRoomType(), room.getCost());
        }
    }

    public void showBasketPrice(){
        System.out.println("[ 총 금액 ]");
        System.out.println();
        System.out.println("W " + basketDatabase.getTotalPrice());
    }

    public void checkBasket(){
        printBasketTitle();
        System.out.println("선택하신 객실을 확인해줏세요.");
        System.out.println();
        showBasketList();
        System.out.println("1. 예약 진행");
        System.out.println("2. 예약 제외");
        System.out.println("3. 돌아가기");
    }

    public void reserveBasket(){ // 1. 예약 진행
        printBasketTitle();
        System.out.println();
        showBasketList();
        System.out.println();
        showBasketPrice();
        System.out.println();
        System.out.println("예약을 진행하시겠습니까?");
        System.out.println();
        System.out.println("1. 확인\t2. 취소");
    }

    public void deleteBasket(){ // 2. 예약 제외
        printBasketTitle();
        System.out.println("예약을 제외할 객실을 선택해주세요.");
        System.out.println();
        showBasketList();
        System.out.println();
    }

    public void confirmDeleteBasket(int index){ // 2. 예약 제외 확인 - index = input - 1
        System.out.println("해당 객식을 예약 목록에서 제외하시겠습니까?");
        System.out.println();
        Room room = basketDatabase.getRoom(index);
        System.out.println("\"- " + room.getRoomName() + " | " + room.getRoomType() + " | W " + room.getCost() + "\"");
        System.out.println();
        System.out.println("1. 확인\t2. 취소");
    }
    public void successRserveBasket(){ // 1. BasketService의 checkMoney가 ture이면
        printBasketTitle();
        basketService.makeBasketToReservation(customer);
        System.out.println("예약이 성공적으로 진행되었습니다.");
        System.out.println();
        // +3초뒤 메인화면으로 이동합니다.
    }

    public void failureReserveBasket(){ // 1. BasketService의 checkMoney가 false이면
        printBasketTitle();
        System.out.println("금액이 부족하여 예약에 실패하였습니다.");
        System.out.println();
        // +3초뒤 메인화면으로 이동합니다.
    }

    public void successDeleteBasket(){ // 2. 예약 제외 완료 -> removeRoom(index) 해야 함
        printBasketTitle();
        System.out.println("해당 객실이 예약 목록에서 제외되었습니다.");
        System.out.println();
        // +3초뒤 메인 화면으로 이동합니다.
    }

}
