package domain;

public enum RoomType {
    MEDIUM("스텐다드"),
    DELUXE("디럭스"),
    SWEET("스위트"),
    SPECIAL("스페셜"),
    LOVE("러브");

    private final String type;

    RoomType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
