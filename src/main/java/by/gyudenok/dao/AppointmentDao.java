package by.gyudenok.dao;

import by.gyudenok.entity.Appointment;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface AppointmentDao<T> extends Dao<Appointment> {
    List<T> readAll() throws DaoException;
}
