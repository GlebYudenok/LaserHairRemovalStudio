package by.gyudenok.dao;

import by.gyudenok.entity.Appointment;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDao extends Dao<Appointment> {
    List<Appointment> readAll() throws SQLException;
}
