package kdh.gurujpa.bootstrap;

import kdh.gurujpa.domain.AuthorUuid;
import kdh.gurujpa.domain.Book;
import kdh.gurujpa.repository.AuthorUuidRepository;
import kdh.gurujpa.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
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

        AuthorUuid authorUuid = new AuthorUuid("testName");
        authorUuidRepository.save(authorUuid);

        authorUuidRepository.findAll().forEach(
                a -> System.out.println("AuthorUuid id = " + a.getId())
        );
    }
}
