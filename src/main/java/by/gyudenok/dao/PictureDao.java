package by.gyudenok.dao;

import by.gyudenok.entity.Picture;

import java.sql.SQLException;
import java.util.List;

public interface PictureDao extends Dao<Picture> {
    List<Picture> readAll() throws SQLException;
}
