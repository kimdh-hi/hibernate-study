package kdh.gurujpa.integration;

import kdh.gurujpa.domain.AuthorUuid;
import kdh.gurujpa.domain.BookUuid;
import kdh.gurujpa.repository.AuthorUuidRepository;
import kdh.gurujpa.repository.BookRepository;
import kdh.gurujpa.repository.BookUuidRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@ComponentScan(basePackages = "kdh.gurujpa.bootstrap")
@DataJpaTest // 기본적으로 h2 in-memory datasource 을 사용한다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // DataJpaTest에 의해 datasource가 자동설정되지 않도록 한다.
public class MySQLIntegrationTest {

    @Autowired BookRepository bookRepository;
    @Autowired BookUuidRepository bookUuidRepository;
    @Autowired AuthorUuidRepository authorUuidRepository;


    @Test
    void authorUuidRepositoryTest() {
        AuthorUuid savedAuthorUuid = authorUuidRepository.save(new AuthorUuid("name"));
        AuthorUuid findAuthorUuid = authorUuidRepository.getById(savedAuthorUuid.getId());

        assertThat(savedAuthorUuid).isNotNull();
        assertThat(savedAuthorUuid.getId()).isNotNull();
        assertThat(findAuthorUuid).isNotNull();
    }

    @Test
    void bookUuidRepositoryTest() {
        BookUuid savedBookUuid = bookUuidRepository.save(new BookUuid("title", "isbn", "pub", null));
        BookUuid findBookUuid = bookUuidRepository.getById(savedBookUuid.getId());

        assertThat(savedBookUuid).isNotNull();
        assertThat(savedBookUuid.getId()).isNotNull();
        assertThat(findBookUuid).isNotNull();
    }

    @Test
    void bookRepositoryTest() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(2);
    }
}
