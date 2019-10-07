package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.Dao;
import by.gyudenok.dao.PictureDao;
import by.gyudenok.entity.Picture;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlPictureDaoImpl implements PictureDao {

    private static final Logger LOGGER = LogManager.getLogger(SqlPictureDaoImpl.class);
    private static final String SQL_INSERT_QUERY = new String("INSERT INTO pictures (id, link) VALUES (?, ?)");
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM PICTURES WHERE id=?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM PICTURES WHERE id=?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE PICTURES SET link=? WHERE id=?");
    private static final String SQL_READ_ALL_QUERY = new String("SELECT *FROM PICTURES");

    @Override
    public boolean create(Picture picture) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_INSERT_QUERY);
            ps.setString(1, picture.getId());
            ps.setString(2, picture.getLink());
            code = ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(code > 0) {
            LOGGER.info("Picture was created successfully!");
            return true;
        }else {
            LOGGER.warn("Cannot insert data");
            throw new DaoException();
        }
    }

    @Override
    public Picture read(String id) throws DaoException {
        Picture picture = null;
        try {
            PreparedStatement ps = ConnectionPool.getInstance().
                    getConnection().prepareStatement(SQL_READ_BY_ID_QUERY);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();

            picture = new Picture();

            if (resultSet.next()) {
                picture.setId(resultSet.getString("id"));
                picture.setLink(resultSet.getString("link"));
            }
            resultSet.close();
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(picture.getId() == null) {
            throw new DaoException();
        }
        return picture;
    }

    @Override
    public int update(Picture entity) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);
            ps.setString(1, entity.getLink());
            ps.setString(2, entity.getId());

            code = ps.executeUpdate();
            if (code > 0) {
                LOGGER.info("Picture was update successful!");
            } else {
                LOGGER.warn("Picture not found or can't update!");
                throw new DaoException();
            }
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }


    @Override
    public int delete(String id) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_DELETE_BY_ID_QUERY);
            ps.setString(1, id);
            code = ps.executeUpdate();
            if (code > 0) {
                LOGGER.info("Picture was delete successfully!");
            } else {
                LOGGER.warn("Picture not fount or can't delete!");
                throw new DaoException();
            }
            ps.close();
        } catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }

    @Override
    public List<Picture> readAll() throws DaoException {
        List<Picture> pictures = null;
        try {
            Statement statement = ConnectionPool.getInstance()
                    .getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_READ_ALL_QUERY);
            pictures = new ArrayList<>();
            while (resultSet.next()) {
                pictures.add(new Picture(
                        resultSet.getString("id"),
                        resultSet.getString("link")
                ));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return pictures;
    }
}
