package FactoryMethodPattern;

public class Main {
    public static void main(String[] args) {
        Library library = Library.getInstance();

        // Thêm sách với Factory Method
        library.addBook("physical", "Harry Potter", "J.K. Rowling", "Fantasy", "");
        library.addBook("ebook", "Sherlock Holmes", "Arthur Conan Doyle", "Detective", "PDF");
        library.addBook("audiobook", "Atomic Habits", "James Clear", "Self-help", "320");

        // Hiển thị danh sách sách
        library.displayAllBooks();
    }
}
