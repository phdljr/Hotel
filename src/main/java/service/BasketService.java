package service;

import data.BasketDatabase;
import data.Hotel;
import data.ReservationDatabase;
import domain.Customer;
import domain.Reservation;
import domain.Room;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BasketService {

    private final BasketDatabase basketDatabase = new BasketDatabase();
    private final ReservationDatabase reservationDatabase = new ReservationDatabase();

    public List<Room> returnBasketList() {
        List<Room> basket = basketDatabase.getBasket();
        return basket;
    }

    public long returnBasketPrice() {
        long totalPrice = basketDatabase.getTotalPrice();
        return totalPrice;
    }

    public Room returnRoomToCancel(int index) {
        Room chosenRoom = basketDatabase.getRoom(index);
        return chosenRoom;
    }

    public boolean checkMoney(Customer customer) { // 돈이 충분한지 판단
        long money = customer.getMoney();
        return basketDatabase.getTotalPrice() <= money;
    }

    public void makeBasketToReservation(Customer customer) { //basket에 담긴 방들을 예약 리스트에 추가
        LocalDateTime datetime = LocalDateTime.now();

        for (Room room : basketDatabase.getBasket()) {
            String uuid = UUID.randomUUID().toString();
            Reservation reservation = new Reservation(uuid, room, customer, datetime);
            reservationDatabase.addReservation(reservation);
        }
        customer.subtractMoney(basketDatabase.getTotalPrice());
        Hotel.AddMoney(basketDatabase.getTotalPrice()); // 호텔에 돈 추가하는 메소드 일단 추가
        basketDatabase.clear();
    }

    public void addRoom(Room room) {
        basketDatabase.addRoom(room);
    }

    public void deleteRoomFromBasket(int index) {
        basketDatabase.removeRoom(index);
    }


}
