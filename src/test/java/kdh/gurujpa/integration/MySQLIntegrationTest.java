package kdh.gurujpa.integration;

import kdh.gurujpa.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@ComponentScan(basePackages = "kdh.gurujpa.bootstrap")
@DataJpaTest // 기본적으로 h2 in-memory datasource 을 사용한다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // DataJpaTest에 의해 datasource가 자동설정되지 않도록 한다.
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void test() {
        long count = bookRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
    }
}
