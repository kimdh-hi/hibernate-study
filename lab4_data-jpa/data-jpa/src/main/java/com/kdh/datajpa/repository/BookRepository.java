package com.kdh.datajpa.repository;

import com.kdh.datajpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);
    Book readByTitle(String title); // EmptyResultDataAccessException
    @Nullable Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    @Query("select b from Book b where b.title = :title")
    Book findBookByTitleWithQuery(@Param("title") String t);

    @Query(value = "select * from book where publisher = :publisher", nativeQuery = true)
    List<Book> findBookListByPublisherWithNativeQuery(String publisher);

    @Query(value = "select * from book where publisher = :publisher limit 1", nativeQuery = true)
    Book findBookByPublisherWithNativeQuery(String publisher);
}
