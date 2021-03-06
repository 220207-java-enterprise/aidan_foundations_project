package com.revature.ers.daos;

import com.revature.ers.dtos.requests.UpdateReimbursementRequest;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Update;
import com.revature.ers.util.ConnectionFactory;
import com.revature.ers.util.exceptions.DataSourceException;
import com.revature.ers.util.exceptions.ResourcePersistenceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {
    private final String rootSelect =
            "SELECT er.*, auth.username AS author, res.username AS resolver " +
            "FROM ers_reimbursements er " +
            "LEFT JOIN ers_users auth ON er.author_id=auth.user_id " +
            "LEFT JOIN ers_users res ON er.resolver_id=res.user_id";

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
    public List<Reimbursement> getByParams(Map<String, Object> paramsMap) {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement query = createSearchQuery(conn, rootSelect, paramsMap);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Reimbursement reimbursement = createReimb(rs);
                reimbursements.add(reimbursement);
            }

            return reimbursements;

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public List<Reimbursement> getAll() {
        List<Reimbursement> reimbs = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.prepareStatement(
                    rootSelect + " WHERE status_id='65882559-1bab-4306-b99a-7b4c1705b7ef'"
            ).executeQuery();

            while (rs.next()) {
                Reimbursement reimb = createReimb(rs);
                reimbs.add(reimb);
            }

            return reimbs;

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    public void updateStatus(UpdateReimbursementRequest update) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
            "UPDATE ers_reimbursements " +
                "SET resolved=?, resolver_id=?, status_id=? " +
                "WHERE reimb_id=? AND status_id='9e10b3e2-734b-4596-a89d-215bd9997691'"
            );
            pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(2, update.getUpdates().getResolverId());
            pstmt.setString(3, update.getUpdates().getStatusId());
            pstmt.setString(4, update.getReimbId());

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

    }

    private Reimbursement createReimb(ResultSet rs) throws SQLException {
        Reimbursement reimb = new Reimbursement();

        reimb.setReimbId(rs.getString("reimb_id"));
        reimb.setAmount(rs.getFloat("amount"));
        reimb.setSubmitted(rs.getTimestamp("submitted"));
        reimb.setResolved(rs.getTimestamp("resolved"));
        reimb.setDescription(rs.getString("description"));
        reimb.setPaymentId(rs.getString("payment_id"));
        reimb.setAuthorId(rs.getString("author_id"));
        reimb.setAuthor(rs.getString("author"));
        reimb.setResolverId(rs.getString("resolver_id"));
        reimb.setResolver(rs.getString("resolver"));
        reimb.setStatusObj(rs.getString("status_id"));
        reimb.setTypeObj(rs.getString("type_id"));

        return reimb;
    }
}
