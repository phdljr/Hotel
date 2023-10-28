package data;

import domain.Customer;
import domain.CustomerType;
import domain.Reservation;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationDatabase {

    private static ReservationDatabase reservationDatabase = new ReservationDatabase();
    private final Map<String, Reservation> reservationMap = new HashMap<>();
    //Test를 위해 hotel 가져옴
    private Hotel hotel = Hotel.getHotel();

    private ReservationDatabase() {
        reservationMap.put("220e8400-e29b-41d4-a716-446655440000",
            new Reservation("220e8400-e29b-41d4-a716-446655440000",
                hotel.getRoomList().get(1),
                new Customer("test1", "이종렬", "010-1234-5678", 1000000000L, CustomerType.CUSTOMER),
                LocalDateTime.now().minusDays(2)));
    }

    public static ReservationDatabase getReservationDatabase() {
        return reservationDatabase;
    }

    public void addReservation(Reservation reservation) {
        reservationMap.put(reservation.getUuid(), reservation);
    }

    public Map<String, Reservation> getReservationMap(String customerId) {
        return reservationMap.entrySet().stream()
            .filter(entry -> entry.getValue().getCustomer().getId().equals(customerId))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Reservation> getAllReservation() {
        return reservationMap.values().stream().toList();
    }

    public void removeReservation(String uuid) {
        reservationMap.get(uuid).getRoom().setReserved(false);
        reservationMap.remove(uuid);
    }
}
