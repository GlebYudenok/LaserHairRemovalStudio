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
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM PICTURES WHERE id=?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DEKETE FROM PICTURES WHERE id=?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE PICTURES SET link=? WHERE id=?");

    @Override
    public void create() throws ClassNotFoundException, SQLException {

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
        return code;
    }
}
