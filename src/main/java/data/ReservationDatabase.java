package data;

import domain.Customer;
import domain.CustomerType;
import domain.Reservation;
import domain.Room;
import domain.RoomType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationDatabase {

    private Map<String, Reservation> reservationMap = new HashMap<>();

    public ReservationDatabase() {
        // TODO DELETE
        reservationMap.put("550e8400-e29b-41d4-a716-446655440000",
            new Reservation("550e8400-e29b-41d4-a716-446655440000",
                new Room(101, RoomType.LARGE, 10L, true),
                new Customer("test1", "이종렬", "010-1234-5678", 1000000000L, CustomerType.CUSTOMER),
                LocalDateTime.now()));
        reservationMap.put("440e8400-e29b-41d4-a716-446655440000",
            new Reservation("440e8400-e29b-41d4-a716-446655440000",
                new Room(102, RoomType.SWEET, 20L, true),
                new Customer("test1", "이종렬", "010-1234-5678", 1000000000L, CustomerType.CUSTOMER),
                LocalDateTime.now()));
        reservationMap.put("330e8400-e29b-41d4-a716-446655440000",
            new Reservation("330e8400-e29b-41d4-a716-446655440000",
                new Room(103, RoomType.SPECIAL, 40L, true),
                new Customer("test1", "이종렬", "010-1234-5678", 1000000000L, CustomerType.CUSTOMER),
                LocalDateTime.now()));
        reservationMap.put("220e8400-e29b-41d4-a716-446655440000",
            new Reservation("220e8400-e29b-41d4-a716-446655440000",
                new Room(104, RoomType.LOVE, 30L, true),
                new Customer("test1", "이종렬", "010-1234-5678", 1000000000L, CustomerType.CUSTOMER),
                LocalDateTime.now()));
    }

    public void addReservation(Reservation reservation) {
        reservationMap.put(reservation.getUuid(), reservation);
    }

    public Map<String, Reservation> getReservationMap(String customerId) {
        return reservationMap.entrySet().stream()
            .filter(entry -> entry.getValue().getCustomer().getId().equals(customerId))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void removeReservation(String uuid) {
        reservationMap.remove(uuid);
    }
}
