package com.kdh.jdbcex;

import com.kdh.jdbcex.dao.AuthorDao;
import com.kdh.jdbcex.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ComponentScan(basePackages = "com.kdh.jdbcex.dao")
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

    @Autowired AuthorDao authorDao;

    @Test
    void getAuthor() {
        Author author = authorDao.getBuId(1L);

        assertThat(author).isNotNull();
    }
}