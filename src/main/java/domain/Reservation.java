package domain;

import java.time.LocalDateTime;

public class Reservation {
    private String uuid;
    private int roomId;
    private String customerId;
    private LocalDateTime dateTime;

    public Reservation(String uuid, int roomId, String customerId,LocalDateTime dateTime){
        this.uuid = uuid;
        this.roomId = roomId;
        this.customerId = customerId;
        this.dateTime = dateTime;
    }

    public String getUuid(){
        return uuid;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
