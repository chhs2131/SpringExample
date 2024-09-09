package graalvm.graalvmexample.web;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final Map<String, Book> books = new ConcurrentHashMap<>();
    static {
        books.put("978-3-16-148410-0", new Book("978-3-16-148410-0", "Effective Java", 45000));
        books.put("978-1-491-94600-8", new Book("978-1-491-94600-8", "Clean Code", 32000));
        books.put("978-0-201-03801-3", new Book("978-0-201-03801-3", "Design Patterns", 50000));
        books.put("978-1-593-27825-5", new Book("978-1-593-27825-5", "Refactoring", 37000));
        books.put("978-1-491-94595-7", new Book("978-1-491-94595-7", "Java Concurrency in Practice", 43000));
        books.put("978-0-596-51617-0", new Book("978-0-596-51617-0", "Head First Design Patterns", 39000));
        books.put("978-0-321-35668-0", new Book("978-0-321-35668-0", "The Pragmatic Programmer", 34000));
        books.put("978-1-491-94730-2", new Book("978-1-491-94730-2", "Spring in Action", 46000));
        books.put("978-0-201-63446-0", new Book("978-0-201-63446-0", "Algorithms", 31000));
        books.put("978-0-321-92870-4", new Book("978-0-321-92870-4", "Java Performance", 42000));
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    public List<Book> getBooks() {
        return books.values().stream().toList();
    }

    public void addBook(Book book) {
        books.put(book.isbn(), book);
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

}
