package lab5.lehuyhung21066381.StrategyPattern;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private SearchStrategy searchStrategy;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(String title, String author, String genre) {
        books.add(new Book(title, author, genre));
        System.out.println("Đã thêm sách: " + title);
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void searchBooks(String keyword) {
        if (searchStrategy == null) {
            System.out.println(" Chưa chọn chiến lược tìm kiếm!");
            return;
        }

        List<Book> result = searchStrategy.search(books, keyword);
        if (result.isEmpty()) {
            System.out.println(" Không tìm thấy sách nào!");
        } else {
            System.out.println("\n Kết quả tìm kiếm:");
            for (Book book : result) {
                book.displayInfo();
            }
        }
    }

    public void displayAllBooks() {
        System.out.println("\n📚 Danh sách sách trong thư viện:");
        for (Book book : books) {
            book.displayInfo();
        }
    }
}
