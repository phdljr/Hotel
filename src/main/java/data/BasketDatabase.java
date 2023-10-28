package data;

import domain.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * 장바구니
 */
public class BasketDatabase {

    private static BasketDatabase basketDatabase = new BasketDatabase();
    private final List<Room> basket = new ArrayList<>();
    long totalPrice = 0;

    private BasketDatabase() {
    }

    public static BasketDatabase getBasketDatabase() {
        return basketDatabase;
    }

    public List<Room> getBasket() {
        return basket;
    }

    public Room getRoom(int index) {
        return basket.get(index);
    }

    public void addRoom(Room room) {
        basket.add(room);
        totalPrice += room.getCost();
    }

    public void removeRoom(int index) {
        totalPrice -= basket.get(index).getCost();
        basket.remove(index);
    }

    public void clear() {
        basket.clear();
        totalPrice = 0;
    }

    public long getTotalPrice() {
        return totalPrice;
    }
}
