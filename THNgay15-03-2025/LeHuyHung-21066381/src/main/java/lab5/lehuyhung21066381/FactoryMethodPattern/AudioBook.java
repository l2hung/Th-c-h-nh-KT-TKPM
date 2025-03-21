package FactoryMethodPattern;

public class AudioBook implements Book {
    private String title;
    private String author;
    private String genre;
    private int duration;

    public AudioBook(String title, String author, String genre, int duration) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    @Override
    public void displayInfo() {
        System.out.println(" Sách nói: " + title + " - " + author + " (" + genre + "), Thời lượng: " + duration + " phút");
    }
}
