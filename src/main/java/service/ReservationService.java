package service;

import data.ReservationDatabase;
import domain.Reservation;
import java.util.Map;

/**
 * 예약에 관련된 로직
 */
public class ReservationService {

    private final ReservationDatabase reservationDatabase = new ReservationDatabase();

    public Map<String, Reservation> getReservationMap(String customerId) {
        return reservationDatabase.getReservationMap(customerId);
    }

    public void removeReservation(String uuid) {
        reservationDatabase.removeReservation(uuid);
    }
}
