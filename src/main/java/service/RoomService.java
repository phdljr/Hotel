package service;

import data.Hotel;
import domain.Room;
import java.util.Map;

/**
 * 객실에 관련된 로직 ex) 방 조회, 해당 방의 예약 여부
 */
public class RoomService {

    private final Hotel hotel = new Hotel();

    public Map<Integer, Room> getRoomList() {
        return hotel.getRoomList();
    }
}
