package domain;

public class Room {
    private int room_id;
    private String roomName;
    private RoomType roomType;
    private double cost;
    private String customer_id;

    public int getRoom_id(){
        return room_id;
    }

    public String getRoomName(){
        return roomName;
    }

    public RoomType getRoomType(){
        return roomType;
    }

    public double getCost(){
        return cost;
    }


}
