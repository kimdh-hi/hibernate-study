package com.kdh.jdbctemplate.dao.book;

import com.kdh.jdbctemplate.domain.Author;
import com.kdh.jdbctemplate.domain.Book;

public interface BookDao {

    Book getById(Long id);
    Book findBookByTitle(String title);
    Book findBookByIsbn(String isbn);
    Book saveBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(Long id);
}
