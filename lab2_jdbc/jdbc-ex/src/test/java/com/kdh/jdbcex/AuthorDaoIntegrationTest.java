package com.kdh.jdbcex;

import com.kdh.jdbcex.dao.AuthorDao;
import com.kdh.jdbcex.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@ComponentScan(basePackages = "com.kdh.jdbcex.dao")
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

    @Autowired AuthorDao authorDao;

    @Test
    void deleteAuthor() {
        //given
        Author author = new Author();
        author.setFirstName("dh");
        author.setLastName("kim");
        Author saveAuthor = authorDao.saveAuthor(author);
        //when
        authorDao.deleteAuthor(saveAuthor.getId());
        //then
        assertThat(authorDao.getById(saveAuthor.getId())).isNull();
    }

    @Test
    void updateAuthor() {
        //given
        Author author = new Author();
        author.setFirstName("dh");
        author.setLastName("kim");
        Author saveAuthor = authorDao.saveAuthor(author);
        Author author2 = new Author();
        author.setFirstName("dh");
        author.setLastName("kim");
        Author saveAuthor2 = authorDao.saveAuthor(author);
        //when
        saveAuthor.setFirstName("daehyun");
        Author updateAuthor = authorDao.updateAuthor(saveAuthor);
        //then
        assertThat(updateAuthor.getFirstName()).isEqualTo("daehyun");
        assertThat(authorDao.getById(saveAuthor2.getId()).getFirstName()).isEqualTo("dh");
    }

    @Test
    void saveAuthor() {
        Author author = new Author();
        author.setFirstName("dh");
        author.setLastName("kim");
        Author save = authorDao.saveAuthor(author);

        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();

    }

    @Test
    void findAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void getAuthor() {
        Author author = authorDao.getById(1L);

        assertThat(author).isNotNull();
    }
}
