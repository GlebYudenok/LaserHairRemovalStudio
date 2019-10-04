package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.Dao;
import by.gyudenok.entity.Appointment;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class SqlAppointmentDaoImpl implements Dao<Appointment> {

    private static final Logger LOGGER = LogManager.getLogger(SqlAppointmentDaoImpl.class);
    private static final String SQL_INSERT_QUERY = new String("INSERT INTO APPOINTMENT (id, date_n_time," +
            "user_id, service_id, complex_id) VALUES (" +
            "?,?,?,?,?)");
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM APPOINTMENT WHERE id=?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM APPOINTMENT WHERE user_id=?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE APPOINTMENT SET " +
            "`date_n_time`=?, `user_id`=?, `service_id`=?, `complex_id`=? WHERE ID=?");

    @Override
    public boolean create(Appointment appointment) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_INSERT_QUERY);
        ps.setString(1, appointment.getId());
        Timestamp timestamp = new Timestamp(appointment.getsDateOfMeet().getTime().getTime());
        ps.setTimestamp(2, timestamp);
        ps.setString(3, appointment.getUserId());
        ps.setString(4, appointment.getServiceId());
        ps.setString(5, appointment.getComplexId());
        boolean ex = ps.execute();
        if(ex == false) {
            LOGGER.warn("Cannot insert data");
        }else {
            LOGGER.info("Appointment was created successfully!");
        }
        ps.close();
        return ex;
    }

    @Override
    public Appointment read(String id) throws SQLException {
        PreparedStatement preparedStatement =
                ConnectionPool.getInstance().getConnection()
                        .prepareStatement(SQL_READ_BY_ID_QUERY);

        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Appointment appointment = null;

        while (resultSet.next()) {
            java.sql.Timestamp timestamp = resultSet.getTimestamp("date_n_time");
            java.util.Date date = new java.util.Date(timestamp.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            appointment = new Appointment(
                    resultSet.getString("id"),
                    calendar,
                    resultSet.getString("user_id"),
                    resultSet.getString("service_id"),
                    resultSet.getString("complex_id")
            );
        }
        resultSet.close();
        preparedStatement.close();
        return appointment;
    }

    @Override
    public int update(Appointment appointment) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);

        Timestamp timestamp = new Timestamp(appointment.getsDateOfMeet().getTime().getTime());

        ps.setTimestamp(1, timestamp);
        ps.setString(2, appointment.getUserId());
        ps.setString(3, appointment.getServiceId());
        ps.setString(4, appointment.getComplexId());
        ps.setString(5,appointment.getId());
        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("Appointment was update successfully!");
        } else {
            LOGGER.warn("Appointment not found or can't update!");
        }
        ps.close();
        return code;
    }

    @Override
    public int delete(String userId) throws SQLException, DaoException {
        PreparedStatement statement = ConnectionPool.getInstance().
                getConnection().prepareStatement(SQL_DELETE_BY_ID_QUERY);
        statement.setString(1, userId);
        int code = statement.executeUpdate();
        if(code > 0) {
            LOGGER.info("Appointment was delete successfully!");
        }else {
            LOGGER.warn("Appointment not found or can't delete!");
        }
        statement.close();
        return code;
    }
}
