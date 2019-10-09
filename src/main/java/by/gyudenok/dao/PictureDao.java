package by.gyudenok.dao;

import by.gyudenok.entity.Picture;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface PictureDao<T> extends Dao<Picture> {
    List<T> readAll() throws DaoException;
}
