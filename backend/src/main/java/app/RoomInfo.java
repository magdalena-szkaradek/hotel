package app;

public class RoomInfo {

    private final int beds;
    private final int price;
    private final String name;

    public RoomInfo(int beds, int price, String name) {
        this.beds = beds;
        this.price = price;
        this.name = name;
    }

    public int getBeds() {
        return beds;
    }

    public int getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }
}
