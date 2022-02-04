package com.kdh.jdbcex.dao;

import com.kdh.jdbcex.domain.Author;

public interface AuthorDao {

    Author getById(Long id);
    Author findAuthorByName(String firstName, String lastName);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}
