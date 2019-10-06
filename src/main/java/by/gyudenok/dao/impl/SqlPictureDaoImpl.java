package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.Dao;
import by.gyudenok.entity.Picture;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlPictureDaoImpl implements Dao<Picture> {

    private static final Logger LOGGER = LogManager.getLogger(SqlPictureDaoImpl.class);
    private static final String SQL_INSERT_QUERY = new String("INSERT INTO pictures (id, link) VALUES (?, ?)");
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM PICTURES WHERE id=?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM PICTURES WHERE id=?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE PICTURES SET link=? WHERE id=?");

    @Override
    public boolean create(Picture picture) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_INSERT_QUERY);
        ps.setString(1, picture.getId());
        ps.setString(2, picture.getLink());
        int code = ps.executeUpdate();
        ps.close();
        if(code > 0) {
            LOGGER.info("Picture was created successfully!");
            return true;
        }else {
            LOGGER.warn("Cannot insert data");
            return false;
        }
    }

    @Override
    public Picture read(String id) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance().
                getConnection().prepareStatement(SQL_READ_BY_ID_QUERY);
        ps.setString(1, id);
        ResultSet resultSet = ps.executeQuery();

        Picture picture = new Picture();

        if (resultSet.next()) {
            picture.setId(resultSet.getString("id"));
            picture.setLink(resultSet.getString("link"));
        }
        resultSet.close();
        ps.close();
        return picture;
    }

    @Override
    public int update(Picture entity) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);
        ps.setString(1, entity.getLink());
        ps.setString(2, entity.getId());

        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("Picture was update successful!");
        }else {
            LOGGER.warn("Picture not found or can't update!");
        }
        ps.close();
        return code;
    }


    @Override
    public int delete(String id) throws SQLException, DaoException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_DELETE_BY_ID_QUERY);
        ps.setString(1, id);
        int code = ps.executeUpdate();
        if (code > 0) {
            LOGGER.info("Picture was delete successfully!");
        }else {
            LOGGER.warn("Picture not fount or can't delete!");
        }
        ps.close();
        return code;
    }
}
