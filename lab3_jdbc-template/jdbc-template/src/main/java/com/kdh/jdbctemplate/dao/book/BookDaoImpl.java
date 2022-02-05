package com.kdh.jdbctemplate.dao.book;

import com.kdh.jdbctemplate.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao{

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("select * from book where id = ?", getMapper(), id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return jdbcTemplate.queryForObject("select * from book where title = ?", getMapper(), title);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return jdbcTemplate.queryForObject("select * from book where isbn = ?", getMapper(), isbn);
    }

    @Override
    public Book saveBook(Book book) {
        jdbcTemplate.update("insert into book(title, isbn, publisher, author_id) values (?, ?, ?, ?)",
                    book.getTitle(), book.getIsbn(), book.getPublisher(), book.getAuthorId());
        Long lastInsertId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);
        return getById(lastInsertId);
    }

    @Override
    public Book updateBook(Book book) {
        jdbcTemplate.update("update book set title=?, isbn=?, publisher=?",
                book.getTitle(), book.getIsbn(), book.getPublisher());
        return getById(book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }

    private  BookMapper getMapper() {
        return new BookMapper();
    }
}
