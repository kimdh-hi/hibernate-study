package com.kdh.jdbcex.dao;

import com.kdh.jdbcex.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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
        Statement statement = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;


        try {
            connection = dataSource.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("select * from author where id = " + id); // SQL injection 취약점

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

        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            pstm = connection.prepareStatement("select * from author where first_name = ? and last_name = ?");
            pstm.setString(1, firstName);
            pstm.setString(2, lastName);
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
    public Author saveAuthor(Author author) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            pstm = connection.prepareStatement("insert into author (first_name, last_name) values (?, ?)");
            pstm.setString(1, author.getFirstName());
            pstm.setString(2, author.getLastName());
            pstm.execute();

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select LAST_INSERT_ID()"); // LAST_INSERT_ID : MySQL함수
            if (resultSet.next()) {
                long savedId = resultSet.getLong(1);
                return getById(savedId);
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
