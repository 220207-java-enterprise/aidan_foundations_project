package com.revature.ers.daos;

import com.revature.ers.models.Update;

import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> {
    // POST
    void save(T newEntity);

    // GET (1)
    T getById(String id);

    // GET (All)
    List<T> getAll();

    // PUT
    void update(Update update);

    // DELETE
    void deleteById(String id);

    default String createUpdateQuery(Update update) {
        StringBuilder setUpdates = new StringBuilder();

        for (int i = 0; i < update.getColumnNames().size(); i++) {
            String s =
                update.getColumnNames().get(i) + "='" +
                update.getColumnUpdates().get(i) + "'";
            setUpdates.append(s);

            if (i < (update.getColumnNames().size() - 1))
                setUpdates.append(", ");
            else
                setUpdates.append(" ");
        }

        return "UPDATE " + update.getTableName() + " " +
               "SET " + setUpdates +
               "WHERE " + update.getIdType() + "= '" + update.getId() + "'";
    }
}
