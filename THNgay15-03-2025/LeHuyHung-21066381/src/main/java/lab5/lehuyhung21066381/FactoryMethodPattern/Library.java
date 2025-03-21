package FactoryMethodPattern;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private List<Book> books;

    private Library() {
        books = new ArrayList<>();
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(String type, String title, String author, String genre, String extraInfo) {
        Book book = BookFactory.createBook(type, title, author, genre, extraInfo);
        books.add(book);
        System.out.println(" Đã thêm sách: " + title);
    }

    public void displayAllBooks() {
        System.out.println("\n Danh sách sách trong thư viện:");
        for (Book book : books) {
            book.displayInfo();
        }
    }
}
