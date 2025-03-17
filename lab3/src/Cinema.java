import java.util.ArrayList;

public class Cinema {
    private String name;
    private ArrayList<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Hall> getHalls() {
        return halls;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }
}
