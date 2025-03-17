import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Session {
    private String filmName;
    private String dateTime;
    private int duration;
    private boolean[][] seats;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Session(String filmName, String dateTime, int duration, int rows, int cols) {
        this.filmName = filmName;
        this.dateTime = dateTime;
        this.duration = duration;
        seats = new boolean[rows][cols];
    }

    public String getFilmName() {
        return filmName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getLocalDateTime() {
        try {
            return LocalDateTime.parse(dateTime, FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean hasAvailableSeats() {
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                if (!seat) return true;
            }
        }
        return false;
    }

    public boolean bookSeat(int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            return false;
        }
        if (seats[row][col]) {
            return false;
        }
        seats[row][col] = true;
        return true;
    }

    public void printSeatPlan() {
        System.out.println("План зала для сеанса \"" + filmName + "\" в " + dateTime + ":");
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                System.out.print(seat ? "[X] " : "[O] ");
            }
            System.out.println();
        }
    }
}
