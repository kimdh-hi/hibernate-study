package com.kdh.jdbcex.dao;

import com.kdh.jdbcex.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Objects;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public Author getById(Long id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            pstm = connection.prepareStatement("select * from author where id = ?");
            pstm.setLong(1, id);
            resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, pstm, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from author where first_name = ? and last_name = ?");
        ) {
            pstm.setString(1, firstName);
            pstm.setString(2, lastName);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return getAuthorFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author saveAuthor(Author author) {

        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
            PreparedStatement pstm = connection.prepareStatement("insert into author (first_name, last_name) values(?, ?)")
        ) {
            pstm.setString(1, author.getFirstName());
            pstm.setString(2, author.getLastName());
            pstm.execute();

            try (ResultSet rs = stmt.executeQuery("select LAST_INSERT_ID()")) {
                if (rs.next()) {
                    long savedId = rs.getLong(1);
                    return getById(savedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstm = connection.prepareStatement("update author set first_name = ?, last_name = ? where id = ?")
        ) {
            pstm.setString(1, author.getFirstName());
            pstm.setString(2, author.getLastName());
            pstm.setLong(3, author.getId());
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getById(author.getId());
    }

    @Override
    public void deleteAuthor(Long id) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstm = connection.prepareStatement("delete from author where id = ?")
        ) {
            pstm.setLong(1, id);
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));

        return author;
    }

    private void closeAll(ResultSet resultSet, PreparedStatement pstm, Connection connection) throws SQLException {
        if (resultSet != null) resultSet.close();
        if (pstm != null) pstm.close();
        if (connection != null) connection.close();
    }
}
