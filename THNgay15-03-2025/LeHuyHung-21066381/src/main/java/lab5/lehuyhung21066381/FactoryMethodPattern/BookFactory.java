package FactoryMethodPattern;

public class BookFactory {
    public static Book createBook(String type, String title, String author, String genre, String extraInfo) {
        switch (type.toLowerCase()) {
            case "physical":
                return new PhysicalBook(title, author, genre);
            case "ebook":
                return new EBook(title, author, genre, extraInfo);
            case "audiobook":
                return new AudioBook(title, author, genre, Integer.parseInt(extraInfo));
            default:
                throw new IllegalArgumentException("Loại sách không hợp lệ!");
        }
    }
}
