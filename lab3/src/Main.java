import java.util.*;
import java.time.LocalDateTime;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Cinema> cinemas = new ArrayList<>();
    private static final String ADMIN_USERNAME = "login";
    private static final String ADMIN_PASSWORD = "parol";

    public static void main(String[] args) {
        SampleData();

        while (true) {
            System.out.println("БИЛЕТНАЯ СИСТЕМА");
            System.out.println("1. Вход администратора");
            System.out.println("2. Вход пользователя");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    adminLogin();
                    break;
                case "2":
                    userMenu();
                    break;
                case "0":
                    System.out.println("Выход из программы");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова");
            }
        }
    }

    private static void adminLogin() {
        System.out.print("Введите логин: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Вход выполнен успешно");
            adminMenu();
        } else {
            System.out.println("Неверный логин или пароль");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nМеню администратора");
            System.out.println("1. Добавить кинотеатр");
            System.out.println("2. Добавить зал в кинотеатр");
            System.out.println("3. Создать сеанс");
            System.out.println("4. Показать все кинотеатры и залы");
            System.out.println("0. Выход из меню администратора");
            System.out.print("Выберите опцию: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addCinema();
                    break;
                case "2":
                    addHall();
                    break;
                case "3":
                    createSession();
                    break;
                case "4":
                    showCinemas();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова");
            }
        }
    }

    private static void addCinema() {
        System.out.print("Введите название кинотеатра: ");
        String name = scanner.nextLine();
        Cinema cinema = new Cinema(name);
        cinemas.add(cinema);
        System.out.println("Кинотеатр добавлен");
    }

    private static void addHall() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет кинотеатров");
            return;
        }
        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).getName());
        }
        int cinemaIndex = readInt("Ваш выбор: ") - 1;
        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор");
            return;
        }
        Cinema selectedCinema = cinemas.get(cinemaIndex);
        System.out.print("Введите название зала: ");
        String hallName = scanner.nextLine();
        int rows = readInt("Введите количество рядов: ");
        int cols = readInt("Введите количество мест в ряду: ");

        Hall hall = new Hall(hallName, rows, cols);
        selectedCinema.addHall(hall);
        System.out.println("Зал добавлен в кинотеатр " + selectedCinema.getName());
    }

    private static void createSession() {
        if(cinemas.isEmpty()){
            System.out.println("Нет кинотеатров");
            return;
        }
        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i+1) + ". " + cinemas.get(i).getName());
        }
        int cinemaIndex = readInt("Ваш выбор: ") - 1;
        if(cinemaIndex < 0 || cinemaIndex >= cinemas.size()){
            System.out.println("Неверный выбор");
            return;
        }
        Cinema selectedCinema = cinemas.get(cinemaIndex);
        if(selectedCinema.getHalls().isEmpty()){
            System.out.println("В этом кинотеатре нет залов");
            return;
        }
        System.out.println("Выберите зал:");
        ArrayList<Hall> halls = selectedCinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i+1) + ". " + halls.get(i).getHallName());
        }
        int hallIndex = readInt("Ваш выбор: ") - 1;
        if(hallIndex < 0 || hallIndex >= halls.size()){
            System.out.println("Неверный выбор");
            return;
        }
        Hall selectedHall = halls.get(hallIndex);

        System.out.print("Введите название фильма: ");
        String filmName = scanner.nextLine();
        System.out.print("Введите время сеанса ");
        String time = scanner.nextLine();
        int duration = readInt("Введите длительность сеанса ");

        Session session = new Session(filmName, time, duration, selectedHall.getRows(), selectedHall.getCols());
        selectedHall.addSession(session);
        System.out.println("Сеанс создан");
    }

    private static void showCinemas() {
        if(cinemas.isEmpty()){
            System.out.println("Нет кинотеатров");
            return;
        }
        for (Cinema cinema : cinemas) {
            System.out.println("Кинотеатр: " + cinema.getName());
            if(cinema.getHalls().isEmpty()){
                System.out.println("  Нет залов");
            } else {
                for(Hall hall : cinema.getHalls()){
                    System.out.println("  Зал: " + hall.getHallName() + " (Ряды: " + hall.getRows() + ", Мест в ряду: " + hall.getCols() + ")");
                    if(hall.getSessions().isEmpty()){
                        System.out.println("    Нет сеансов");
                    } else {
                        for(Session session : hall.getSessions()){
                            System.out.println("    Сеанс: " + session.getFilmName() + " в " + session.getDateTime() +
                                    " длительностью " + session.getDuration() + " мин. " +
                                    (session.hasAvailableSeats() ? "Есть свободные места" : "Нет свободных мест"));
                        }
                    }
                }
            }
        }
    }

    private static void userMenu() {
        while(true) {
            System.out.println("\nМеню Пользователя");
            System.out.println("1. Купить билет");
            System.out.println("2. Найти ближайший сеанс по фильму");
            System.out.println("3. Показать план зала для сеанса");
            System.out.println("0. Выход из пользовательского меню");
            System.out.print("Выберите опцию: ");

            String choice = scanner.nextLine();
            switch(choice) {
                case "1":
                    buyTicket();
                    break;
                case "2":
                    findNextSession();
                    break;
                case "3":
                    printHallPlan();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова");
            }
        }
    }

    private static void buyTicket() {
        if(cinemas.isEmpty()){
            System.out.println("Нет кинотеатров");
            return;
        }
        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i+1) + ". " + cinemas.get(i).getName());
        }
        int cinemaIndex = readInt("Ваш выбор: ") - 1;
        if(cinemaIndex < 0 || cinemaIndex >= cinemas.size()){
            System.out.println("Неверный выбор");
            return;
        }
        Cinema cinema = cinemas.get(cinemaIndex);
        if(cinema.getHalls().isEmpty()){
            System.out.println("В этом кинотеатре нет залов");
            return;
        }
        System.out.println("Выберите зал:");
        ArrayList<Hall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i+1) + ". " + halls.get(i).getHallName());
        }
        int hallIndex = readInt("Ваш выбор: ") - 1;
        if(hallIndex < 0 || hallIndex >= halls.size()){
            System.out.println("Неверный выбор");
            return;
        }
        Hall hall = halls.get(hallIndex);
        if(hall.getSessions().isEmpty()){
            System.out.println("В этом зале нет сеансов");
            return;
        }
        System.out.println("Выберите сеанс:");
        ArrayList<Session> sessions = hall.getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            System.out.println((i+1) + ". " + sessions.get(i).getFilmName() + " в " + sessions.get(i).getDateTime() +
                    " длительностью " + sessions.get(i).getDuration() + " мин.");
        }
        int sessionIndex = readInt("Ваш выбор: ") - 1;
        if(sessionIndex < 0 || sessionIndex >= sessions.size()){
            System.out.println("Неверный выбор");
            return;
        }
        Session session = sessions.get(sessionIndex);
        System.out.print("Введите номер ряда: ");
        int row = readInt("") - 1;
        System.out.print("Введите номер места в ряду: ");
        int col = readInt("") - 1;

        if(session.bookSeat(row, col)){
            System.out.println("Билет успешно куплен.");
        } else {
            System.out.println("Место занято или неверный ввод");
        }
    }

    private static void findNextSession() {
        System.out.print("Введите название фильма: ");
        String filmName = scanner.nextLine();
        LocalDateTime now = LocalDateTime.now();
        Session nextSession = null;
        Cinema foundCinema = null;
        Hall foundHall = null;

        for(Cinema cinema : cinemas) {
            for(Hall hall : cinema.getHalls()){
                for(Session session : hall.getSessions()){
                    if(session.getFilmName().equalsIgnoreCase(filmName) && session.hasAvailableSeats()){
                        LocalDateTime sessionTime = session.getLocalDateTime();
                        if(sessionTime != null && sessionTime.isAfter(now)) {
                            if(nextSession == null || sessionTime.isBefore(nextSession.getLocalDateTime())) {
                                nextSession = session;
                                foundCinema = cinema;
                                foundHall = hall;
                            }
                        }
                    }
                }
            }
        }
        if(nextSession != null) {
            System.out.println("Ближайший сеанс: " + nextSession.getFilmName() + " в " + nextSession.getDateTime() +
                    " в кинотеатре \"" + foundCinema.getName() + "\", зал \"" + foundHall.getHallName() + "\"");
        } else {
            System.out.println("Нет доступных сеансов для данного фильма");
        }
    }

    private static void printHallPlan() {
        if(cinemas.isEmpty()){
            System.out.println("Нет кинотеатров.");
            return;
        }
        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i+1) + ". " + cinemas.get(i).getName());
        }
        int cinemaIndex = readInt("Ваш выбор: ") - 1;
        if(cinemaIndex < 0 || cinemaIndex >= cinemas.size()){
            System.out.println("Неверный выбор");
            return;
        }
        Cinema cinema = cinemas.get(cinemaIndex);
        if(cinema.getHalls().isEmpty()){
            System.out.println("В этом кинотеатре нет залов");
            return;
        }
        System.out.println("Выберите зал:");
        ArrayList<Hall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i+1) + ". " + halls.get(i).getHallName());
        }
        int hallIndex = readInt("Ваш выбор: ") - 1;
        if(hallIndex < 0 || hallIndex >= halls.size()){
            System.out.println("Неверный выбор");
            return;
        }
        Hall hall = halls.get(hallIndex);
        if(hall.getSessions().isEmpty()){
            System.out.println("В этом зале нет сеансов");
            return;
        }
        System.out.println("Выберите сеанс:");
        ArrayList<Session> sessions = hall.getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            System.out.println((i+1) + ". " + sessions.get(i).getFilmName() + " в " + sessions.get(i).getDateTime());
        }
        int sessionIndex = readInt("Ваш выбор: ") - 1;
        if(sessionIndex < 0 || sessionIndex >= sessions.size()){
            System.out.println("Неверный выбор");
            return;
        }
        Session session = sessions.get(sessionIndex);
        session.printSeatPlan();
    }

    private static int readInt(String prompt) {
        int num = -1;
        while (true) {
            System.out.print(prompt);
            try {
                num = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неправельный ввод, введите число");
            }
        }
        return num;
    }

    private static void SampleData() {
        Cinema cinema1 = new Cinema("Cinema One");
        Hall hall1 = new Hall("Main Hall", 5, 5);
        Session session1 = new Session("Film A", "12.05.2025 15:30", 120, hall1.getRows(), hall1.getCols());
        hall1.addSession(session1);
        cinema1.addHall(hall1);
        cinemas.add(cinema1);

        Cinema cinema2 = new Cinema("Cinema Two");
        Hall hall3 = new Hall("Big Hall", 11, 11);
        Hall hall4 = new Hall("Small Hall", 4, 4);
        Session session3 = new Session("Film C", "08.02.2025 11:30", 110, hall3.getRows(), hall3.getCols());
        Session session4 = new Session("Film D", "22.11.2025 18:00", 95, hall4.getRows(), hall4.getCols());
        hall3.addSession(session3);
        hall4.addSession(session4);
        cinema2.addHall(hall3);
        cinema2.addHall(hall4);
        cinemas.add(cinema2);
    }
}
