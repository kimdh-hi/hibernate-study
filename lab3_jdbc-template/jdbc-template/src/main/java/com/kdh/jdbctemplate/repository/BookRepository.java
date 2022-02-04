package com.kdh.jdbctemplate.repository;

import com.kdh.jdbctemplate.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
