package com.kdh.jdbcex.repository;

import com.kdh.jdbcex.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}