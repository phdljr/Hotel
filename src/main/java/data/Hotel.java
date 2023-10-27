package data;

import domain.Room;
import domain.RoomType;
import java.util.HashMap;
import java.util.Map;

public class Hotel {

    private static long money;
    private Map<Integer, Room> roomList = new HashMap<Integer, Room>();

    public Hotel() {
        roomList.put(1, new Room(101, RoomType.MEDIUM, 5400000L, false));
        roomList.put(2, new Room(102, RoomType.MEDIUM, 5450000L, false));
        roomList.put(3, new Room(103, RoomType.MEDIUM, 4800000L, true));
        roomList.put(4, new Room(201, RoomType.LARGE, 9990000L, true));
        roomList.put(5, new Room(202, RoomType.MEDIUM, 5980000L, false));
        roomList.put(6, new Room(203, RoomType.LARGE, 9795000L, true));
        roomList.put(7, new Room(301, RoomType.SWEET, 16700000L, true));
        roomList.put(8, new Room(302, RoomType.SWEET, 17000000L, true));
        roomList.put(9, new Room(303, RoomType.SPECIAL, 37000000L, false));
        roomList.put(10, new Room(401, RoomType.LOVE, 20000000L, true));
        roomList.put(11, new Room(401, RoomType.SPECIAL, 30000000L, true));
        roomList.put(12, new Room(401, RoomType.LOVE, 24000000L, false));
    }

    public Hotel(Map<Integer, Room> roomList) {
        this.roomList = roomList;
    }

    public static void AddMoney(long cost) {
        money += cost;
    }

    public Map<Integer, Room> getRoomList() {
        return roomList;
    }

}
