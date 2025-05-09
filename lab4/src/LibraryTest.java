import java.util.*;

class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" %s (%d)", title, author, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return year == other.year
                && Objects.equals(title, other.title)
                && Objects.equals(author, other.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }
}

class Library {
    private List<Book> books;
    private Set<String> uniqueAuthors;
    private Map<String, Integer> authorCount;

    public Library() {
        books = new ArrayList<>();
        uniqueAuthors = new HashSet<>();
        authorCount = new HashMap<>();
    }

    // добавить книгу в библиотеку
    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            uniqueAuthors.add(book.getAuthor());
            authorCount.put(
                    book.getAuthor(),
                    authorCount.getOrDefault(book.getAuthor(), 0) + 1
            );
        }
    }

    // удалить книгу из библиотеки
    public void removeBook(Book book) {
        if (books.remove(book)) {
            String author = book.getAuthor();
            int count = authorCount.get(author) - 1;
            if (count <= 0) {
                authorCount.remove(author);
                uniqueAuthors.remove(author);
            } else {
                authorCount.put(author, count);
            }
        }
    }

    // найти все книги определенного автора
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                result.add(b);
            }
        }
        return result;
    }

    // найти все книги, изданные в определенный год
    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getYear() == year) {
                result.add(b);
            }
        }
        return result;
    }

    //  вывести список всех книг в библиотеке
    public void printAllBooks() {
        System.out.println("Cписок всех книг:");
        for (Book b : books) {
            System.out.println(b);
        }
        System.out.println();
    }

    // вывести список уникальных авторов
    public void printUniqueAuthors() {
        System.out.println("Cписок уникальных авторов:");
        for (String author : uniqueAuthors) {
            System.out.println(author);
        }
        System.out.println();
    }

    // вывести статистику по количеству книг каждого автора
    public void printAuthorStatistics() {
        System.out.println("Количество книг автора:");
        for (Map.Entry<String, Integer> entry : authorCount.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}

public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Мастер и Маргарита", "Михаил Булгаков", 1967);
        Book book2 = new Book("Преступление и наказание", "Фёдор Достоевский", 1866);
        Book book3 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book4 = new Book("1984", "Джордж Оруэлл", 1949);
        Book book5 = new Book("Собачье сердце", "Михаил Булгаков", 1925);
        Book book6 = new Book("Тихий Дон", "Михаил Шолохов", 1940);
        Book book7 = new Book("Анна Каренина", "Лев Толстой", 1877);
        Book book8 = new Book("Идиот", "Фёдор Достоевский", 1869);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
        library.addBook(book7);
        library.addBook(book8);


        library.printAllBooks();

        List<Book> t1booksByAuth = library.findBooksByAuthor("Лев Толстой");
        System.out.println("Книги Льва Толстого:");
        for (Book b : t1booksByAuth) {
            System.out.println(b);
        }
        System.out.println();

        List<Book> books1869 = library.findBooksByYear(1869);
        System.out.println("Книги изданные в 1869г:");
        for (Book b : books1869) {
            System.out.println(b);
        }
        System.out.println();

        library.printUniqueAuthors();

        library.printAuthorStatistics();

        library.removeBook(book1);
        System.out.println("После удаления книги: 'Мастер и Маргарита' Михаил Булгаков 1967");
        library.printAllBooks();
    }
}
