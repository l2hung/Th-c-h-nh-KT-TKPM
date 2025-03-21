package StrategyPattern;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Thêm sách vào thư viện
        library.addBook("Harry Potter", "J.K. Rowling", "Fantasy");
        library.addBook("Sherlock Holmes", "Arthur Conan Doyle", "Detective");
        library.addBook("Atomic Habits", "James Clear", "Self-help");
        library.addBook("Clean Code", "Robert C. Martin", "Programming");

        // Hiển thị danh sách sách
        library.displayAllBooks();

        while (true) {
            System.out.println("\nChọn kiểu tìm kiếm:");
            System.out.println("1 - Tìm theo tiêu đề");
            System.out.println("2 - Tìm theo tác giả");
            System.out.println("3 - Tìm theo thể loại");
            System.out.println("0 - Thoát");
            System.out.print("Nhập lựa chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println(" Thoát chương trình!");
                break;
            }

            System.out.print("Nhập từ khóa tìm kiếm: ");
            String keyword = scanner.nextLine();

            switch (choice) {
                case 1:
                    library.setSearchStrategy(new SearchByTitle());
                    break;
                case 2:
                    library.setSearchStrategy(new SearchByAuthor());
                    break;
                case 3:
                    library.setSearchStrategy(new SearchByGenre());
                    break;
                default:
                    System.out.println(" Lựa chọn không hợp lệ!");
                    continue;
            }

            library.searchBooks(keyword);
        }

        scanner.close();
    }
}
