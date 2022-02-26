package com.revature.ers.daos;

import com.revature.ers.models.User;
import com.revature.ers.util.ConnectionFactory;
import com.revature.ers.util.exceptions.DataSourceException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User newModel) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<User>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.prepareStatement("SELECT * FROM ers_users").executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIsActive(rs.getBoolean("is_active"));
                user.setRoleId(rs.getString("role_id"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return users;
    }

    @Override
    public void update(User updateModel) {

    }

    @Override
    public void deleteById(String id) {

    }
}
