package com.kdh.datajpa.dao;

import com.kdh.datajpa.domain.Author;
import com.kdh.datajpa.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AuthorDaoImpl implements AuthorDao{

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Author findAuthor = authorRepository.getById(author.getId());
        findAuthor.setFirstName(author.getFirstName());
        findAuthor.setLastName(author.getLastName());

        return authorRepository.save(findAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
