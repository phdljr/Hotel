package domain;

public class Room {
    private int roomId;
    private String roomName;
    private RoomType roomType;
    private long cost;
    private String customerId;

    public int getRoomId(){
        return roomId;
    }

    public String getRoomName(){
        return roomName;
    }

    public RoomType getRoomType(){
        return roomType;
    }

    public long getCost(){
        return cost;
    }


}
