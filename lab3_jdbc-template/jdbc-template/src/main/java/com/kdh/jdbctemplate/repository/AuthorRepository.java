package com.kdh.jdbctemplate.repository;

import com.kdh.jdbctemplate.domain.Author;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
