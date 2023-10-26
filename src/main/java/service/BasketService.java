package service;

import data.BasketDatabase;
import data.ReservationDatabase;
import domain.Customer;
import domain.Reservation;
import domain.Room;
import io.output.BasketOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BasketService {

    private final BasketDatabase basketDatabase;
    private final ReservationDatabase reservationDatabase;
    private final BasketOutput basketOutput;
    private final Customer customer;

    public BasketService(BasketDatabase basketDatabase, ReservationDatabase reservationDatabase,
        BasketOutput basketOutput, Customer customer) {
        this.basketDatabase = basketDatabase;
        this.reservationDatabase = reservationDatabase;
        this.basketOutput = basketOutput;
        this.customer = customer;
    }

    public void printInitView() {
        String customerName = customer.getName();
        List<Room> basket = basketDatabase.getBasket();
        basketOutput.printBasketTitle(customerName);
        basketOutput.printBasketText();
        basketOutput.printBasketList(basket);
        basketOutput.checkBasketMenu();
    }

    public void printReserveView() {
        String customerName = customer.getName();
        List<Room> basket = basketDatabase.getBasket();
        long totalPrice = basketDatabase.getTotalPrice();
        basketOutput.printBasketTitle(customerName);
        basketOutput.printBasketList(basket);
        basketOutput.printBasketPrice(totalPrice);
        basketOutput.reserveBasket();
    }

    public void printResultOfReserveView() {
        //예약 가능한지 확인
        if (checkMoney(customer)) {
            basketOutput.successReserveBasket();
        } else {
            basketOutput.failureReserveBasket();
        }
    }

    public void cancelReserveView() {
        String customerName = customer.getName();
        List<Room> basket = basketDatabase.getBasket();
        basketOutput.printBasketTitle(customerName);
        basketOutput.deleteBasket();
        basketOutput.printBasketList(basket);
    }

    public void confirmCancelView(int index) {
        String customerName = customer.getName();
        Room chosenRoom = basketDatabase.getRoom(index);
        basketOutput.printBasketTitle(customerName);
        basketOutput.confirmDeleteBasket(chosenRoom);
    }

    public void successCancelView() {
        String customerName = customer.getName();
        basketOutput.printBasketTitle(customerName);
        basketOutput.successDeleteBasket();
    }

    public void printBasketTitle() {
        String customerName = customer.getName();
        basketOutput.printBasketTitle(customerName);
    }

    public void printBasketList() {
        List<Room> basket = basketDatabase.getBasket();
        basketOutput.printBasketList(basket);
    }

    public void printBasketPrice() {
        long totalPrice = basketDatabase.getTotalPrice();
        basketOutput.printBasketPrice(totalPrice);
    }

    public void confirmDeleteBasket(int index) {
        Room chosenRoom = basketDatabase.getRoom(index);
        basketOutput.confirmDeleteBasket(chosenRoom);
    }

    public boolean checkMoney(Customer customer) { // 돈이 충분한지 판단
        long money = customer.getMoney();
        return basketDatabase.getTotalPrice() <= money;
    }

    public void makeBasketToReservation(Customer customer) { //basket에 담긴 방들을 예약 리스트에 추가
        LocalDateTime datetime = LocalDateTime.now();

        for (Room room : basketDatabase.getBasket()) {
            String uuid = UUID.randomUUID().toString();
            Reservation reservation = new Reservation(uuid, room.getRoomId(), customer.getId(),
                datetime);
            reservationDatabase.addReservation(reservation);
        }
        customer.subtractMoney(basketDatabase.getTotalPrice());
        basketDatabase.clear();
    }

    public void successReserveBasket() {
        makeBasketToReservation(customer);
        basketOutput.successReserveBasket();
    }

}
