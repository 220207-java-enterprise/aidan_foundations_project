package com.revature.ers.daos;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Update;
import com.revature.ers.util.ConnectionFactory;
import com.revature.ers.util.exceptions.DataSourceException;
import com.revature.ers.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {

    @Override
    public void save(Reimbursement newReimbursement) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO ers_reimbursements " +
                    "VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            pstmt.setString(1, newReimbursement.getReimbId());
            pstmt.setFloat(2, newReimbursement.getAmount());
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(4, null);
            pstmt.setString(5, newReimbursement.getDescription());
            pstmt.setString(6, null);
            pstmt.setString(7, newReimbursement.getAuthorId());
            pstmt.setString(8, null);
            pstmt.setString(9, newReimbursement.getStatusId());
            pstmt.setString(10, newReimbursement.getTypeId());

            System.out.println(pstmt);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist reimbursement to database");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public Reimbursement getById(String id) {
        return null;
    }

    @Override
    public List<Reimbursement> getAll() {
        return null;
    }

    @Override
    public void update(Update update) {

    }

    @Override
    public void deleteById(String id) {

    }
}
