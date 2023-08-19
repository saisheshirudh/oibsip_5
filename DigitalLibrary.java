import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    private User borrower;
    private Date dueDate;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public User getBorrower() {
        return borrower;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void borrow(User user, Date dueDate) {
        isAvailable = false;
        borrower = user;
        this.dueDate = dueDate;
    }

    public void returnBook() {
        isAvailable = true;
        borrower = null;
        dueDate = null;
    }
}

class User {
    private String username;
    private List<Book> borrowedBooks;
    private List<Book> reservedBooks;

    public User(String username) {
        this.username = username;
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<Book> getReservedBooks() {
        return reservedBooks;
    }

    public void borrowBook(Book book, Date dueDate) {
        if (!book.isAvailable()) {
            System.out.println("The book is not available for borrowing.");
            return;
        }

        borrowedBooks.add(book);
        book.borrow(this, dueDate);
        System.out.println("Book '" + book.getTitle() + "' borrowed successfully.");
    }

    public void returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            System.out.println("You didn't borrow this book.");
            return;
        }

        borrowedBooks.remove(book);
        book.returnBook();
        System.out.println("Book '" + book.getTitle() + "' returned successfully.");
    }

    public void reserveBook(Book book) {
        if (book.isAvailable()) {
            System.out.println("You can't reserve an available book.");
            return;
        }

        reservedBooks.add(book);
        System.out.println("Book '" + book.getTitle() + "' reserved successfully.");
    }

    public void cancelReservation(Book book) {
        reservedBooks.remove(book);
        System.out.println("Reservation for book '" + book.getTitle() + "' cancelled.");
    }
}

public class DigitalLibrary {
    public static void main(String[] args) {
        Book book1 = new Book("Introduction to Java", "John Smith");
        Book book2 = new Book("Programming in Python", "Jane Doe");

        User user1 = new User("user1");
        User user2 = new User("user2");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 14); // Two weeks from now
        Date dueDate = calendar.getTime();

        user1.borrowBook(book1, dueDate);
        user2.borrowBook(book2, dueDate);

        user1.returnBook(book1);

        user2.reserveBook(book2);
        user2.cancelReservation(book2);
    }
}
