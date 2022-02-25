package com.revature.ers.daos;

import com.revature.ers.models.User;
import com.revature.ers.util.ConnectionFactory;
import com.revature.ers.util.exceptions.DataSourceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User newModel) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }


        return null;
    }

    @Override
    public void update(User updateModel) {

    }

    @Override
    public void deleteById(String id) {

    }
}
