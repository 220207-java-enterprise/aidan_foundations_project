package com.revature.ers.daos;

import com.revature.ers.models.Update;
import com.revature.ers.models.User;
import com.revature.ers.util.ConnectionFactory;
import com.revature.ers.util.exceptions.DataSourceException;
import com.revature.ers.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    private final String rootSelect = "SELECT * FROM ers_users";

    @Override
    public void save(User newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO ers_users" +
                        "(user_id, username, email, password, given_name, surname, role_id)" +
                    "VALUES" +
                        "(?, ?, ?, ?, ?, ?, ?)"
            );

            pstmt.setString(1, newUser.getUserId());
            pstmt.setString(2, newUser.getUsername());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPassword());
            pstmt.setString(5, newUser.getGivenName());
            pstmt.setString(6, newUser.getSurname());
            pstmt.setString(7, newUser.getRoleId());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist user to database.");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public User getById(String userId) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                rootSelect + " WHERE user_id=?"
            );
            pstmt.setString(1, userId);

            System.out.println(pstmt);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return createUser(rs);
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return null;
    }

    public User getByUsernameAndPassword(String username, String password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(
                rootSelect + " WHERE username=? AND password=?"
            );
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return createUser(rs);
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return null;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.prepareStatement(rootSelect).executeQuery();
            while (rs.next()) {
                User user = createUser(rs);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return users;
    }

    @Override
    public void update(Update update) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(this.createUpdateQuery(update));

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("rowsUpdated: " + rowsUpdated);

            if (rowsUpdated != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist user to database.");
            }

            conn.commit();
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void deleteById(String id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM ers_users " +
                    "WHERE user_id=?"
            );
            pstmt.setString(1, id);

            System.out.println(pstmt);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to delete user from database");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setUserId(rs.getString("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setGivenName(rs.getString("given_name"));
        user.setSurname(rs.getString("surname"));
        user.setIsActive(rs.getBoolean("is_active"));
        user.setRoleId(rs.getString("role_id"));

        return user;
    }
}