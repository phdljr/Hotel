package data;

import domain.Reservation;
import java.util.HashMap;
import java.util.Map;

public class ReservationDatabase {
    private Map<String, Reservation> reservationMap = new HashMap<>();

    public void addReservation(Reservation reservation){
        reservationMap.put(reservation.getUuid(), reservation);
    }
}
