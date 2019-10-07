package by.gyudenok.dao;

import by.gyudenok.entity.Appointment;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface AppointmentDao extends Dao<Appointment> {
    List<Appointment> readAll() throws DaoException;
}
