package com.revature.ers.daos;

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
    void update(String id, T updatedEntity);

    // DELETE
    void deleteById(String id);

    default String createUpdateQuery (
            String tableName,
            String idType,
            String id,
            List<String> columnNames
    ) {
        System.out.println("columnNames: " + columnNames.toString());

        StringBuilder columnUpdates = new StringBuilder();

        for (String s : columnNames) {
            s = s + "=?, ";
            columnUpdates.append(s);
        }

        return "UPDATE " + tableName + " " +
               "SET " + columnUpdates +
               "WHERE " + idType + "=" + id;
    }
}
