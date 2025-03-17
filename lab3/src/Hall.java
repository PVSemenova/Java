import java.util.ArrayList;

public class Hall {
    private String hallName;
    private int rows;
    private int cols;
    private ArrayList<Session> sessions;

    public Hall(String hallName, int rows, int cols) {
        this.hallName = hallName;
        this.rows = rows;
        this.cols = cols;
        this.sessions = new ArrayList<>();
    }

    public String getHallName() {
        return hallName;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }
}
