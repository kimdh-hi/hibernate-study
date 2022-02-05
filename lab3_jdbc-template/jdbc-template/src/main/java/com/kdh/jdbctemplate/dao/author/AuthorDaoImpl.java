package com.kdh.jdbctemplate.dao.author;

import com.kdh.jdbctemplate.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return jdbcTemplate.queryForObject("select * from author where id = ?", getAuthorMapper(), id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject(
                "select * from author where first_name = ? and last_name = ?",
                getAuthorMapper(),
                firstName, lastName);
    }

    @Override
    public Author saveAuthor(Author author) {
        jdbcTemplate.update("insert into author (first_name, last_name) values (?, ?)", author.getFirstName(), author.getLastName());
        Long lastInsertId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        return getById(lastInsertId);
    }

    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update(
                "update author set first_name = ?, last_name = ? where id = ?",
                author.getFirstName(), author.getLastName(), author.getId());
        return getById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        jdbcTemplate.update("delete from author where id = ?", id);
    }

    private AuthorMapper getAuthorMapper() {
        return new AuthorMapper();
    }
}
