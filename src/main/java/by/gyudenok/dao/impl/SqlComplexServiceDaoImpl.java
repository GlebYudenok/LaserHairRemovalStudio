package by.gyudenok.dao.impl;

import by.gyudenok.dao.ComplexServiceDao;
import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.entity.ComplexService;
import by.gyudenok.entity.Gender;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public boolean create(ComplexService complexService) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_INSERT_QUERY);
        ps.setString(1, complexService.getId());
        ps.setString(2, complexService.getServiceIds().get(0));
        ps.setBigDecimal(3, complexService.getPrice());
        ps.setString(4, complexService.getComplexName());
        ps.setString(5, complexService.getGender().name());

        boolean ex = ps.execute();
        if(ex == false) {
            LOGGER.warn("Cannot create ComplexService");
        }else {
            LOGGER.info("ComplexService was created successfully!");
        }
        ps.close();
        return ex;
    }

    @Override
    public ComplexService read(String id) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance().
                getConnection().prepareStatement(SQL_READ_BY_ID_QUERY);

        ps.setString(1, id);
        ResultSet resultSet = ps.executeQuery();
        ComplexService service = null;
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
       return service;
    }

    /*
        Wrong update cause we set what from List of ids will update manually.
     */
    @Override
    public int update(ComplexService entity, String serviceId) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);

        ps.setString(1, entity.getServiceIds().get(0));
        ps.setBigDecimal(2, entity.getPrice());
        ps.setString(3, entity.getComplexName());
        ps.setString(4, entity.getGender().name());
        ps.setString(5, entity.getId());
        ps.setString(6, serviceId);

        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("Complex service was successful update!");
        }else {
            LOGGER.warn("Complex not found or can't update!");
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
        if(code > 0) {
            LOGGER.info("Complex was delete successfully!");
        } else {
            LOGGER.warn("Complex not found or can't delete!");
        }
        ps.close();
        return code;
    }
}
