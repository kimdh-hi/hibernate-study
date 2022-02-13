package com.kdh.datajpa;

import com.kdh.datajpa.domain.Book;
import com.kdh.datajpa.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void readByTitleTest() {
        assertThrows(EmptyResultDataAccessException.class,
                () -> bookRepository.readByTitle("no-no-no-book"));

    }

    @Test
    void getByTitleNullableResultTest() {
        Book book = bookRepository.getByTitle("no-no-no-book");

        assertNull(book);
    }

    @Test
    void getByTitleNullableParamTest() {
        Book book = bookRepository.getByTitle(null);

        assertNull(book);
    }

    @DisplayName("Stream 타입으로 조회하기")
    @Test
    void findByTitleReturnTypeStreamTest() {
        AtomicInteger count = new AtomicInteger();

        bookRepository.findAllByTitleNotNull().forEach(
                book -> count.incrementAndGet()
        );

        assertThat(count.get()).isEqualTo(5);
    }
}
