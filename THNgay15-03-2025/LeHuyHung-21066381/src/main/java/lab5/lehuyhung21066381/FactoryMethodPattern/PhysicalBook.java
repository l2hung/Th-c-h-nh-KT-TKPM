package FactoryMethodPattern;

public class PhysicalBook implements Book {
    private String title;
    private String author;
    private String genre;

    public PhysicalBook(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public void displayInfo() {
        System.out.println(" Sách giấy: " + title + " - " + author + " (" + genre + ")");
    }
}
