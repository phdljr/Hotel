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

    private final BasketDatabase basketDatabase = new BasketDatabase();
    private final ReservationDatabase reservationDatabase = new ReservationDatabase();
    private final BasketOutput basketOutput = new BasketOutput();


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
            Reservation reservation = new Reservation(uuid, room.getNumber(), customer.getId(),
                datetime);
            reservationDatabase.addReservation(reservation);
        }
        customer.subtractMoney(basketDatabase.getTotalPrice());
        basketDatabase.clear();
    }

    public void deleteRoomFromBasket(int index) {
        basketDatabase.removeRoom(index);
    }

    public void waitThread() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
