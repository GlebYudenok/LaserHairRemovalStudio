package by.gyudenok.dao.impl;

import by.gyudenok.dao.ComplexServiceDao;
import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.Dao;
import by.gyudenok.entity.ComplexService;
import by.gyudenok.entity.Gender;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlComplexServiceDaoImpl implements ComplexServiceDao<ComplexService> {

    private static final Logger LOGGER = LogManager.getLogger(SqlComplexServiceDaoImpl.class);
    private static final String SQL_INSERT_QUERY = new String("INSERT INTO COMPLEX_SERVICE (id, service_id, price, name, user_gender) " +
            "VALUES (?,?,?,?,?)");
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM COMPLEX_SERVICE WHERE id=?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM COMPLEX_SERVICE WHERE id=?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE complex_service SET service_id=?, " +
            "price=?, name=?, user_gender=? WHERE id=? and service_id=?");
    private static final String SQL_READ_ALL_QUERY = new String("SELECT *FROM COMPLEX_SERVICE");
    private static final String SQL_READ_BY_NAME = new String("SELECT *FROM COMPLEX_SERVICE WHERE name=?");

    @Override
    public boolean create(ComplexService complexService) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_INSERT_QUERY);
            ps.setString(1, complexService.getId());
            ps.setString(2, complexService.getServiceIds().get(0));
            ps.setBigDecimal(3, complexService.getPrice());
            ps.setString(4, complexService.getComplexName());
            ps.setString(5, complexService.getGender().name());

            code = ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(code > 0) {
            LOGGER.info("Complex service was created successfully!");
            return true;
        }else {
            LOGGER.warn("Cannot insert data");
            throw new DaoException();
        }
    }

    @Override
    public ComplexService read(String id) throws DaoException {
        ComplexService service = null;
        try {
            PreparedStatement ps = ConnectionPool.getInstance().
                    getConnection().prepareStatement(SQL_READ_BY_ID_QUERY);

            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            List<String> serviceList = new ArrayList<>();

            if (resultSet.first()) {
                service = new ComplexService(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        Gender.valueOf(resultSet.getString("user_gender").toUpperCase()),
                        resultSet.getBigDecimal("price")
                );
            }

            do {
                serviceList.add(resultSet.getString("service_id"));
            } while (resultSet.next());
            service.setServiceIds(serviceList);
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
             throw new DaoException();
        }
       return service;
    }

    /*
        Wrong update cause we set what from List of ids will update manually.
     */
    @Override
    public int update(ComplexService entity, String serviceId) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);

            ps.setString(1, entity.getServiceIds().get(0));
            ps.setBigDecimal(2, entity.getPrice());
            ps.setString(3, entity.getComplexName());
            ps.setString(4, entity.getGender().name());
            ps.setString(5, entity.getId());
            ps.setString(6, serviceId);

            code = ps.executeUpdate();
            if (code > 0) {
                LOGGER.info("Complex service was successful update!");
            } else {
                LOGGER.warn("Complex not found or can't update!");
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
                LOGGER.info("Complex was delete successfully!");
            } else {
                LOGGER.warn("Complex not found or can't delete!");
                throw new DaoException();
            }
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }

    @Override
    public List<ComplexService> readAll() throws DaoException {
        List<ComplexService> services = new ArrayList<>();
        try {
            Statement statement = ConnectionPool.getInstance().getConnection()
                    .createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_READ_ALL_QUERY);
            while (resultSet.next()) {
                ComplexService cs =
                        new ComplexService(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                Gender.valueOf(
                                        resultSet.getString("user_gender").toUpperCase()),
                                resultSet.getBigDecimal("price")
                        );
                List<String> servicesIds = new ArrayList<>();
                servicesIds.add(resultSet.getString("service_id"));
                cs.setServiceIds(servicesIds);
                services.add(cs);
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return services;
    }

    @Override
    public ComplexService readByName(String name) throws DaoException {
        ComplexService service = null;
        try {
            PreparedStatement ps = ConnectionPool.getInstance().
                    getConnection().prepareStatement(SQL_READ_BY_NAME);

            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();

            List<String> serviceList = new ArrayList<>();
            service = new ComplexService();

            while (resultSet.next()) {
                serviceList.add(resultSet.getString("service_id"));
                if (resultSet.isFirst()) {
                    service.setGender(Gender.valueOf(resultSet.
                            getString("user_gender").toUpperCase()));
                    service.setPrice(resultSet.getBigDecimal("price"));
                    service.setComplexName(resultSet.getString("name"));
                    service.setId(resultSet.getString("id"));
                }
            }
            service.setServiceIds(serviceList);
            resultSet.close();
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        if (service.getId() == null) {
            throw new NullPointerException();
        }
        return service;
    }
}
