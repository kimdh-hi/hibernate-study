package kdh.gurujpa.repository;

import kdh.gurujpa.bootstrap.DataInitializer;
import kdh.gurujpa.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DataInitializer.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired BookRepository bookRepository;

    @Test
    void saveTest() {
        Book book = new Book("testTitle", "testISBM", "testPub");
        bookRepository.save(book);

        long count = bookRepository.count();
        Assertions.assertThat(count).isEqualTo(3);
    }
}
