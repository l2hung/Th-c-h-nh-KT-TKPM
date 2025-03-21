package StrategyPattern;

import java.util.ArrayList;
import java.util.List;

public class SearchByTitle implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword)) {
                result.add(book);
            }
        }
        return result;
    }
}
