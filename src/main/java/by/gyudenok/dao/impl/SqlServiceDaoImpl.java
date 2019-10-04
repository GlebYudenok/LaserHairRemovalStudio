package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.Dao;
import by.gyudenok.entity.Service;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlServiceDaoImpl implements Dao<Service> {

    private static final Logger LOGGER = LogManager.getLogger(SqlServiceDaoImpl.class);
    private static final String SQL_INSERT_QUERY = new String("INSERT INTO SERVICE (" +
            "id,zone_name,price) VALUES (?,?,?)");
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM SERVICE WHERE ID=?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM SERVICE WHERE ID=?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE SERVICE SET zone_name=?, price=? WHERE id=?");

    @Override
    public boolean create(Service service) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_INSERT_QUERY);
        ps.setString(1, service.getId());
        ps.setString(2, service.getZoneName());
        ps.setBigDecimal(3, service.getPrice());

        boolean ex = ps.execute();
        if(ex == false) {
            LOGGER.warn("Cannot create Service");
        }else {
            LOGGER.info("Service was created successfully!");
        }
        ps.close();
        return ex;
    }

    @Override
    public Service read(String id) throws SQLException {
        PreparedStatement statement = ConnectionPool.getInstance().
                getConnection().prepareStatement(SQL_READ_BY_ID_QUERY);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        Service service = new Service();
        if(resultSet.next()) {
            service.setId(resultSet.getString("id"));
            service.setPrice(resultSet.getBigDecimal("price"));
            service.setZoneName(resultSet.getString("zone_name"));
        }
        resultSet.close();
        statement.close();
        return service;
    }

    @Override
    public int update(Service entity) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);
        ps.setString(1, entity.getZoneName());
        ps.setBigDecimal(2, entity.getPrice());
        ps.setString(3, entity.getId());
        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("Service was update successful!");
        }else {
            LOGGER.info("Service not found or can't update!");
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
            LOGGER.info("Service was delete successfully!");
        } else {
            LOGGER.warn("Service not found or can't delete!");
        }
        ps.close();
        return code;
    }
}
