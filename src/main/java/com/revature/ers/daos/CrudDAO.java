package com.revature.ers.daos;

import com.revature.ers.models.Update;

import javax.naming.event.ObjectChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CrudDAO<T> {
    // POST
    void save(T newEntity);

    // Get (By Search Param)
    List<T> getByParams(Map<String, Object> params);

    // GET (All)
    List<T> getAll();

    // PUT
    void update(Update update);

    // DELETE
    void deleteById(String id);

    default PreparedStatement createSearchQuery(Connection conn, String rootSelect, Map<String, Object> paramsMap) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder(rootSelect).append(" WHERE ");

        List<Map.Entry<String, Object>> params = new ArrayList<>(paramsMap.entrySet());

        for (int i = 0; i < params.size(); i++) {
            queryBuilder.append(params.get(i).getKey());

            if (i == params.size() - 1)
                queryBuilder.append("=?");
            else
                queryBuilder.append("=? AND ");
        }

        PreparedStatement query = conn.prepareStatement(queryBuilder.toString());
        int i = 1;
        for (
                Map.Entry<String, Object> paramsEntry :
                new ArrayList<>(paramsMap.entrySet())
        ) {
            Object value = paramsEntry.getValue();

            if (value instanceof String)
                query.setString(i++, (String) value);
            if (value instanceof Boolean)
                query.setBoolean(i++, (Boolean) value);
        }

        return query;
    }

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
