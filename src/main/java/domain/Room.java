package domain;

public class Room {

    private int number;
    private RoomType roomType;
    private long cost;
    private boolean reserved;

    public Room(int number, RoomType roomType, long cost, boolean reserved) {
        this.number = number;
        this.roomType = roomType;
        this.cost = cost;
        this.reserved = reserved;
    }

    public int getNumber() {
        return number;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public long getCost() {
        return cost;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

}
