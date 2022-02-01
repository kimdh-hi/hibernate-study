package kdh.gurujpa.bootstrap;

import kdh.gurujpa.domain.Book;
import kdh.gurujpa.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Book book1 = new Book("book1", "1234", "book1 pub");
        Book book2 = new Book("book2", "2345", "book2 pub");

        Book savedBook1 = bookRepository.save(book1);
        Book savedBook2 = bookRepository.save(book2);
    }
}
