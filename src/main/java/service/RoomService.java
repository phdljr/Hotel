package service;


import data.Hotel;
import domain.Reservation;
import domain.Room;
import java.util.List;
import java.util.Map;


/**
 * 객실에 관련된 로직 ex) 방 조회, 해당 방의 예약 여부
 */
public class RoomService {

    private final Hotel hotel = Hotel.getHotel();
    private ReservationService reservationService = new ReservationService();

    public Map<Integer, Room> getRoomList() {
        return hotel.getRoomList();
    }

    public void resetReserved() {
        List<Reservation> reservationList = reservationService.getAllReservation();
        for (Reservation reservation : reservationList) {
            reservation.getRoom().setReserved(!reservation.isPassed());
        }
    }
}
