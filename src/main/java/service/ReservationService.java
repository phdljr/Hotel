package service;

import data.ReservationDatabase;
import domain.Reservation;
import java.util.List;
import java.util.Map;

/**
 * 예약에 관련된 로직
 */
public class ReservationService {

    private final ReservationDatabase reservationDatabase = new ReservationDatabase();

    public Map<String, Reservation> getReservationMap(String customerId) {
        return reservationDatabase.getReservationMap(customerId);
    }

    public List<Reservation> getAllReservation() {
        return reservationDatabase.getAllReservation();
    }

    public void removeReservation(String uuid) {
        reservationDatabase.removeReservation(uuid);
    }
}
