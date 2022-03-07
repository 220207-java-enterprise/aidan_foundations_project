package com.revature.ers.daos;

import com.revature.ers.dtos.requests.UpdateUserRequest;
import com.revature.ers.models.Update;
import com.revature.ers.models.User;
import com.revature.ers.util.ConnectionFactory;
import com.revature.ers.util.exceptions.DataSourceException;
import com.revature.ers.util.exceptions.ResourceNotFoundException;
import com.revature.ers.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO implements CrudDAO<User> {

    private final String rootSelect = "SELECT * FROM ers_users";

    @Override
    public void save(User newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO ers_users " +
                    "VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            pstmt.setString(1, newUser.getUserId());
            pstmt.setString(2, newUser.getUsername());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPassword());
            pstmt.setString(5, newUser.getGivenName());
            pstmt.setString(6, newUser.getSurname());
            pstmt.setBoolean(7, newUser.getIsApproved());
            pstmt.setBoolean(8, newUser.getIsActive());
            pstmt.setString(9, newUser.getRoleId());

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

    public void approve(UpdateUserRequest request) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE ers_users " +
                    "SET is_approved=true, is_active=true " +
                    "WHERE user_id=? AND is_approved=false"
            );
            pstmt.setString(1, request.getUserId());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated != 1) {
                conn.rollback();

                if (rowsUpdated == 0)
                    throw new ResourceNotFoundException("No user found with provided id.");

                throw new ResourcePersistenceException("Failed to approve user");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public List<User> getByParams(Map<String, Object> paramsMap) {
        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement query = createSearchQuery(conn, rootSelect, paramsMap);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                User user = createUser(rs);
                users.add(user);
            }

            return users;

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

    public User getByUsername(String username) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(
                rootSelect + " WHERE username=?"
            );
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return createUser(rs);
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return null;
    }

    public User getByEmail(String email) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(
                    rootSelect + " WHERE email=?"
            );
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return createUser(rs);
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return null;
    }

    public List<User> getPending() {
        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.prepareStatement(
                    rootSelect +
                            " WHERE is_approved=false"
            ).executeQuery();

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
    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.prepareStatement(
                            rootSelect +
                            " WHERE is_active=true"
                           ).executeQuery();
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
        user.setIsApproved(rs.getBoolean("is_approved"));
        user.setIsActive(rs.getBoolean("is_active"));
        user.setUserRoleObj(rs.getString("role_id"));

        return user;
    }
}