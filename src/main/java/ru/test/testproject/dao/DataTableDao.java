package ru.test.testproject.dao;

import ru.test.testproject.model.db_entity.DataTable;
import ru.test.testproject.model.dictionary.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.test.testproject.datasource.DataSource;
import ru.test.testproject.model.type.Status;
import ru.test.testproject.utils.PgObjectFactory;

public class DataTableDao {
    private static final Logger LOG = Logger.getLogger(DataTableDao.class.getName());

    public boolean insert(DataTable dt) {
        try (Connection connection = DataSource.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(Sql.DataTable.INSERT);

            ps.setObject(1, PgObjectFactory.getUuidObject(dt.getGuid().toString()));
            ps.setTimestamp(2, dt.getTimestamp());
            ps.setInt(3, dt.getStatus().getValue());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Unable to insert entity to database\nMessage: " +
                    ((e.getMessage() == null || e.getMessage().isEmpty()) ? e.toString() : e.getMessage()));

            return false;
        }
    }

    public boolean updateTimeStampAndStatus(DataTable dt) {
        try (Connection connection = DataSource.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(Sql.DataTable.UPDATE_TIMESTAMP_AND_STATUS);
            ps.setTimestamp(1, dt.getTimestamp());
            ps.setInt(2, dt.getStatus().getValue());
            ps.setObject(3, PgObjectFactory.getUuidObject(dt.getGuid().toString()));

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Unable to update entity in database\nMessage: " +
                    ((e.getMessage() == null || e.getMessage().isEmpty()) ? e.toString() : e.getMessage()));

            return false;

        }
    }

    public DataTable getTaskByGuid(String guid) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(Sql.DataTable.SELECT_BY_GUID)) {

            ps.setObject(1, PgObjectFactory.getUuidObject(guid));
            ps.execute();

            ResultSet resultSet = ps.getResultSet();
            
            if(!resultSet.isBeforeFirst()) {
                return new DataTable(Status.NOT_FOUND);
            }

            resultSet.next();
            Timestamp timestamp = resultSet.getTimestamp(1);
            int status = resultSet.getInt(2);

            return new DataTable(guid, timestamp, Status.getByValue(status));

        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Unable to update entity in database\nMessage: " +
                    ((e.getMessage() == null || e.getMessage().isEmpty()) ? e.toString() : e.getMessage()));

            return null;
        }
    }
}
