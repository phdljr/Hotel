package service;

import data.Hotel;
import data.ReservationDatabase;
import domain.Customer;
import domain.Reservation;
import java.util.List;
import java.util.Map;

/**
 * 예약에 관련된 로직
 */
public class ReservationService {

    private final ReservationDatabase reservationDatabase = ReservationDatabase.getReservationDatabase();

    public Map<String, Reservation> getReservationMap(String customerId) {
        return reservationDatabase.getReservationMap(customerId);
    }

    public List<Reservation> getAllReservation() {
        return reservationDatabase.getAllReservation();
    }

    public void removeReservation(Reservation reservation, Customer customer) {
        reservationDatabase.removeReservation(reservation.getUuid());
        Hotel.addMoney(-reservation.getRoom().getCost());
        customer.subtractMoney(-reservation.getRoom().getCost());
    }
}
