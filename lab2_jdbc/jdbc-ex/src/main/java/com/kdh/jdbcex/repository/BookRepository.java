package com.kdh.jdbcex.repository;

import com.kdh.jdbcex.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}