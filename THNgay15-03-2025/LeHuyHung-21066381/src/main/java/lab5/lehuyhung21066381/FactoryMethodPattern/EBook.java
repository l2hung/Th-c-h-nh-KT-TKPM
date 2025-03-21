package FactoryMethodPattern;

public class EBook implements Book {
    private String title;
    private String author;
    private String genre;
    private String fileFormat;

    public EBook(String title, String author, String genre, String fileFormat) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.fileFormat = fileFormat;
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

    public String getFileFormat() {
        return fileFormat;
    }

    @Override
    public void displayInfo() {
        System.out.println(" Sách điện tử: " + title + " - " + author + " (" + genre + "), Định dạng: " + fileFormat);
    }
}
