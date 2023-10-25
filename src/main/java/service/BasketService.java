package service;

import data.BasketDatabase;
import data.ReservationDatabase;
import domain.Customer;
import domain.Reservation;
import domain.Room;

import java.time.LocalDateTime;
import java.util.*;

public class BasketService {

    private final BasketDatabase basketDatabase;
    private final ReservationDatabase reservationDatabase;

    public BasketService(BasketDatabase basketDatabase, ReservationDatabase reservationDatabase){
        this.basketDatabase = basketDatabase;
        this.reservationDatabase = reservationDatabase;
    }

    public boolean checkMoney(Customer customer){ // 돈이 충분한지 판단
        long money = customer.getMoney();
        return basketDatabase.getTotalPrice() <= money;
    }

    public void makeBasketToReservation(String customer_id){ //basket에 담긴 방들을 예약 리스트에 추가
        LocalDateTime datetime = LocalDateTime.now();

        for(Room room : basketDatabase.getBasket()){
            String uuid = UUID.randomUUID().toString();
            Reservation reservation = new Reservation(uuid, room.getRoom_id(),customer_id, datetime);
            reservationDatabase.addReservation(reservation);
        }
        basketDatabase.clear();
    }

}
