package org.example.dao;

import org.example.maper.RowMapper;
import org.example.model.Account;
import org.example.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    //language=SQL
    private static final String SQL_INSERT_ACCOUNT = "insert into account(username, password) values (?, ?)";
    //language=SQL
    private static final String SQL_UPDATE_ACCOUNT = "update account set username = ?, password = ? where id = ?";
    //language=SQL
    private static final String SQL_DELETE_ACCOUNT = "update account set deleted = true where id = ?";
    //language=SQL
    private static final String SQL_FIND_ALL_ACCOUNT = "select * from account ";
    //language=SQL
    private static final String SQL_FIND_BY_ID_ACCOUNT = "select * from account where id = ?";
    //language=SQL
    private static final String SQL_FIND_PASSWORD_BY_USERNAME = "select * from account where username = ?";
    //language=SQL
    private static final String SQL_INSERT_FAVOURITES_MANGA  = "insert into account_manga values (?, ?)";
    //language=SQL
    private static final String SQL_FIND_DELETED = "select deleted from account where username = ?";
    //language=SQL
    private static final String SQL_UPDATE_USERNAME_BY_ID = "update account set username = ? where id = ?";
    //language=SQL
    private static final String SQL_UPDATE_PASSWORD_BY_ID = "update account set password = ? where id = ?";


    private static final RowMapper<Account> ACCOUNT_ROW_MAPPER = resultSet -> {
        try {
            return new Account(resultSet.getLong("id"),resultSet.getString("username"),
                    resultSet.getString("password"));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    };

    private final Connection connection = DatabaseConnectionUtil.getConnection();


    @Override
    public Account get(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_ACCOUNT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return ACCOUNT_ROW_MAPPER.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something goes wrong", e);
        }

        return null;
    }

    @Override
    public List<Account> getAll() {
        List<Account> authors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACCOUNT)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(ACCOUNT_ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return authors;
    }

    @Override
    public void save(Account account) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ACCOUNT)) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ACCOUNT)){
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String getPasswordByName(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_USERNAME)){
            preparedStatement.setString(1, username);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something goes wrong", e);
        }
        return null;
    }

    @Override
    public boolean isDeleted(String username) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_DELETED)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("deleted");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public void updateUsername(String newUsername, Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USERNAME_BY_ID)){
            statement.setString(1, newUsername);
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updatePassword(String newPassword, Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD_BY_ID)){
            statement.setString(1, newPassword);
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void createBooking(Account user, Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_FAVOURITES_MANGA)) {
            statement.setLong(1, user.getId());
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String getRole(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select role from account where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something goes wrong", e);
        }
        return null;
    }
}

