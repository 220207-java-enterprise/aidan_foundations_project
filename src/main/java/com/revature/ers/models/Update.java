package com.revature.ers.models;

import java.util.List;

public class Update {
    final private String tableName;
    final private String idType;
    final private String id;
    final private List<String> columnNames;
    final private List<String> columnUpdates;

    public Update(
            String tableName,
            String idType,
            String id,
            List<String> columnNames,
            List<String> columnUpdates
    ) {
        this.tableName = tableName;
        this.idType = idType;
        this.id = id;
        this.columnNames = columnNames;
        this.columnUpdates = columnUpdates;
    }

    public String getTableName() {
        return tableName;
    }

    public String getIdType() {
        return idType;
    }

    public String getId() {
        return id;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<String> getColumnUpdates() {
        return columnUpdates;
    }
}