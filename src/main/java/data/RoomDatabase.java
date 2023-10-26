package data;

import domain.Room;

import java.util.HashMap;
import java.util.Map;

public class RoomDatabase {
    private final Map<Integer, Room> roomMap = new HashMap();

    public RoomDatabase() {
        roomMap.put(1, new Room());
        roomMap.put(2, new Room());
        roomMap.put(3, new Room());
    }
}
