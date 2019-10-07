package by.gyudenok.dao.impl;

import by.gyudenok.dao.AppointmentDao;
import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.Dao;
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
    public boolean create(Appointment appointment) throws DaoException {
        PreparedStatement ps = null;
        int code = 0;
        try {
            ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_INSERT_QUERY);
        ps.setString(1, appointment.getId());
        Timestamp timestamp = new Timestamp(appointment.getsDateOfMeet().getTime().getTime());
        ps.setTimestamp(2, timestamp);
        ps.setString(3, appointment.getUserId());
        ps.setString(4, appointment.getServiceId());
        ps.setString(5, appointment.getComplexId());
        code = ps.executeUpdate();
        ps.close();
        } catch (SQLException e) {
            throw new DaoException();
        }
        if(code > 0) {
            LOGGER.info("Appointment was created successfully!");
            return true;
        }else {
            LOGGER.warn("Cannot insert data");
            throw new DaoException();
        }
    }

    @Override
    public Appointment read(String id) throws DaoException {
        Appointment appointment = null;
        try {
            PreparedStatement preparedStatement =
                    ConnectionPool.getInstance().getConnection()
                            .prepareStatement(SQL_READ_BY_ID_QUERY);

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        } catch (SQLException e) {
            throw new DaoException();
        }
        if(appointment == null) {
            throw new DaoException("appointment with this id doesn't exist!");
        }
        return appointment;
    }

    @Override
    public int update(Appointment appointment) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);

            Timestamp timestamp = new Timestamp(appointment.getsDateOfMeet().getTime().getTime());

            ps.setTimestamp(1, timestamp);
            ps.setString(2, appointment.getUserId());
            ps.setString(3, appointment.getServiceId());
            ps.setString(4, appointment.getComplexId());
            ps.setString(5, appointment.getId());
            code = ps.executeUpdate();
            if (code > 0) {
                LOGGER.info("Appointment was update successfully!");
            } else {
                LOGGER.warn("Appointment not found or can't update!");
                throw new DaoException();
            }
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }

    @Override
    public int delete(String userId) throws DaoException {
        int code = 0;
        try {
            PreparedStatement statement = ConnectionPool.getInstance().
                    getConnection().prepareStatement(SQL_DELETE_BY_ID_QUERY);
            statement.setString(1, userId);
            code = statement.executeUpdate();
            if (code > 0) {
                LOGGER.info("Appointment was delete successfully!");
            } else {
                LOGGER.warn("Appointment not found or can't delete!");
                throw new DaoException();
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }

    @Override
    public List<Appointment> readAll() throws DaoException {
        List<Appointment> appointments = new ArrayList<>();
        try {
            Statement statement = ConnectionPool.getInstance()
                    .getConnection().createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL_QUERY);
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
        }catch (SQLException e) {
            throw new DaoException();
        }
        return appointments;
    }
}
