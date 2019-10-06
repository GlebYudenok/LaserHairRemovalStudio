package by.gyudenok.dao.impl;

import by.gyudenok.dao.AppointmentDao;
import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.entity.Appointment;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SqlAppointmentDaoImpl implements AppointmentDao {

    private static final Logger LOGGER = LogManager.getLogger(SqlAppointmentDaoImpl.class);
    private static final String SQL_INSERT_QUERY = new String("INSERT INTO APPOINTMENT (id, date_n_time," +
            "user_id, service_id, complex_id) VALUES (" +
            "?,?,?,?,?)");
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM APPOINTMENT WHERE id=?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM APPOINTMENT WHERE user_id=?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE APPOINTMENT SET " +
            "`date_n_time`=?, `user_id`=?, `service_id`=?, `complex_id`=? WHERE ID=?");
    private static final String SQL_READ_ALL_QUERY = new String("SELECT *FROM APPOINTMENT");

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
        int code = ps.executeUpdate();
        ps.close();
        if(code > 0) {
            LOGGER.info("Appointment was created successfully!");
            return true;
        }else {
            LOGGER.warn("Cannot insert data");
            return false;
        }
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

    @Override
    public List<Appointment> readAll() throws SQLException {
        Statement statement = ConnectionPool.getInstance()
                .getConnection().createStatement();
        ResultSet rs = statement.executeQuery(SQL_READ_ALL_QUERY);
        List<Appointment> appointments = new ArrayList<>();
        while (rs.next()) {
            java.sql.Timestamp timestamp = rs.getTimestamp("date_n_time");
            java.util.Date date = new java.util.Date(timestamp.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            appointments.add(new Appointment(
                    rs.getString("id"),
                    calendar,
                    rs.getString("user_id"),
                    rs.getString("service_id"),
                    rs.getString("complex_id")
            ));
        }
        return appointments;
    }
}
