package by.gyudenok.dao;

import by.gyudenok.entity.Picture;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface PictureDao extends Dao<Picture> {
    List<Picture> readAll() throws DaoException;
}
