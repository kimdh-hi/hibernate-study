package kdh.gurujpa.bootstrap;

import kdh.gurujpa.domain.Book;
import kdh.gurujpa.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        Book book1 = new Book("book1", "1234", "book1 pub", null);
        Book book2 = new Book("book2", "2345", "book2 pub", null);
        Book savedBook1 = bookRepository.save(book1);
        Book savedBook2 = bookRepository.save(book2);

        bookRepository.findAll().forEach(
                b -> System.out.println("Book id = " + b.getId())
        );
    }
}
