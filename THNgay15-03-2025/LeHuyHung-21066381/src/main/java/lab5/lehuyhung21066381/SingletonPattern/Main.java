package SingletonPattern;

public class Main {
    public static void main(String[] args) {
        // Lấy thể hiện duy nhất của Library
        Library library = Library.getInstance();

        // Thêm sách vào thư viện
        library.addBook(new Book("Harry Potter", "J.K. Rowling", "Fantasy"));
        library.addBook(new Book("Sherlock Holmes", "Arthur Conan Doyle", "Detective"));

        // Hiển thị danh sách sách
        System.out.println("Danh sách sách trong thư viện:");
        for (Book book : library.getBooks()) {
            System.out.println("- " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getGenre());
        }

        // Tìm kiếm sách theo tiêu đề
        System.out.println("\nTìm sách theo tiêu đề 'Harry Potter':");
        for (Book book : library.searchByTitle("Harry Potter")) {
            System.out.println("- " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getGenre());
        }
    }
}
