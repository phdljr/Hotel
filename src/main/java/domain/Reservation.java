package domain;

import java.time.LocalDateTime;

public class Reservation {
    private String uuid;
    private int room_id;
    private String customer_id;
    private LocalDateTime DateTime;

    public Reservation(String uuid, int room_id, String customer_id,LocalDateTime DateTime){
        this.uuid = uuid;
        this.room_id = room_id;
        this.customer_id = customer_id;
        this.DateTime = DateTime;
    }

    public String getUuid(){
        return uuid;
    }
}
