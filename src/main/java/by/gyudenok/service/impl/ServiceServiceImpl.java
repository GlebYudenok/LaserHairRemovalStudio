package by.gyudenok.service.impl;

import by.gyudenok.dao.ServiceDao;
import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Service;
import by.gyudenok.exception.DaoException;
import by.gyudenok.exception.ServiceException;
import by.gyudenok.exception.ValidatorException;
import by.gyudenok.service.ServiceService;
import by.gyudenok.service.validator.ServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ServiceServiceImpl implements ServiceService<Service> {

    private static final Logger LOGGER = LogManager.getLogger(ServiceService.class);
    private static final SqlDaoFactory factory = SqlDaoFactory.getInstance();
    private static final ServiceDao<Service> serviceDao = factory.getSqlServiceDao();
    private static final ServiceValidator validator = new ServiceValidator();

    @Override
    public List getServicesList() throws ServiceException {
        List<Service> services;
        try {
            services = serviceDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        LOGGER.info("Services was got successfully!");
        return services;
    }

    @Override
    public boolean createService(Service service) throws ServiceException {
        try {
            validator.checkServiceNull(service);
            serviceDao.create(service);
        }catch (DaoException | ValidatorException e) {
            throw new ServiceException(e.getMessage());
        }
        LOGGER.info("Service was create successfully!");
        return true;
    }

    @Override
    public boolean editService(Service service) throws ServiceException {
        try {
            validator.checkServiceNull(service);
            serviceDao.update(service);
        }catch (DaoException | ValidatorException e) {
            throw new ServiceException(e.getMessage());
        }
        LOGGER.info("Service was edited successfully!");
        return true;
    }

    @Override
    public boolean deleteService(String serviceId) throws ServiceException {
        try {
            validator.checkIdNull(serviceId);
            serviceDao.delete(serviceId);
        }catch (DaoException | ValidatorException e) {
            throw new ServiceException(e.getMessage());
        }
        LOGGER.info("Service was delete successfully!");
        return true;
    }

    @Override
    public Service getService(Service service) throws ServiceException {
        Service readingService = null;
        try {
            validator.checkServiceNull(service);
            validator.checkIdNull(service.getId());
            readingService = serviceDao.read(service.getId());
        }catch (DaoException | ValidatorException e) {
            throw new ServiceException(e.getMessage());
        }
        LOGGER.info("Service was read successfully!");
        return readingService;
    }
}
