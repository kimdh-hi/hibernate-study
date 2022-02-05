package com.kdh.jdbctemplate;

import com.kdh.jdbctemplate.dao.book.BookDao;
import com.kdh.jdbctemplate.domain.Book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ComponentScan(basePackages = {"com.kdh.jdbctemplate.dao"})
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Test
    void saveTest() {
        Book book = new Book("testTitle", "testISBN", "testPub");
        book.setAuthorId(1L);

        Book save = bookDao.saveBook(book);

        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
    }

    @Test
    void getByIdTest() {
        Book book = new Book("testTitle", "testISBN", "testPub");
        book.setAuthorId(1L);
        Book save = bookDao.saveBook(book);

        Book find = bookDao.getById(save.getId());

        assertThat(find).isNotNull();
        assertThat(find.getId()).isEqualTo(save.getId());

    }

    @Test
    void findByTitleTest() {
        final String title = "Spring in Action, 5th Edition";
        Book find = bookDao.findBookByTitle(title);

        assertThat(find).isNotNull();
        assertThat(find.getTitle()).isEqualTo(title);
    }

    @Test
    void findByISBNTest() {
        final String isbn = "978-1617292545";
        Book find = bookDao.findBookByIsbn(isbn);

        assertThat(find).isNotNull();
        assertThat(find.getIsbn()).isEqualTo(isbn);
    }

    @Test
    void updateBookTest() {
        Book book = new Book("testTitle", "testISBN", "testPub");
        book.setAuthorId(1L);
        Book save = bookDao.saveBook(book);

        String updateTitle = "updateTitle";
        String updateISBN = "123-124125115";
        String updatePub = "updatePub";
        save.setTitle(updateTitle);
        save.setIsbn(updateISBN);
        save.setPublisher(updatePub);
        Book update = bookDao.updateBook(save);

        assertThat(update).isNotNull();
        assertThat(update.getTitle()).isEqualTo(updateTitle);
        assertThat(update.getIsbn()).isEqualTo(updateISBN);
        assertThat(update.getPublisher()).isEqualTo(updatePub);
    }

    @Test
    void deleteBookTest() {
        Book book = new Book("testTitle", "testISBN", "testPub");
        book.setAuthorId(1L);
        Book save = bookDao.saveBook(book);

        bookDao.deleteBookById(save.getId());

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(save.getId()));
    }
}
