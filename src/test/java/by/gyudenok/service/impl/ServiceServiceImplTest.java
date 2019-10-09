package by.gyudenok.service.impl;

import by.gyudenok.entity.Service;
import by.gyudenok.exception.DaoException;
import by.gyudenok.exception.ServiceException;
import by.gyudenok.service.ServiceService;
import by.gyudenok.service.factory.ServiceFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class ServiceServiceImplTest {

    private static final ServiceFactory factory = ServiceFactory.getInstance();
    private static final ServiceService<Service> service = factory.getsServiceService();
    private static Service sService = null;

    @BeforeClass
    public static void init() throws DaoException {
        sService = new Service("testId", "testZone", new BigDecimal(228));
    }

    @Test
    public void getServicesList() throws ServiceException {
        createService();
        int size = service.getServicesList().size();
        boolean actual = false;
        if(size > 0) {
            actual = true;
        }
        Assert.assertTrue(actual);
        deleteService();
    }

    @Test
    public void createService() throws ServiceException {
        boolean actual = service.createService(sService);
        Assert.assertTrue(actual);
    }

    @Test
    public void editService() throws ServiceException {
        boolean actual = service.editService
                (new Service("testId", "newName", new BigDecimal(1337)));
        Assert.assertTrue(actual);
        deleteService();
    }

    @Test
    public void deleteService() throws ServiceException {
        boolean actual = service.deleteService("testId");
        Assert.assertTrue(actual);
    }

    @Test
    public void getService() throws ServiceException {
        createService();
        Service getService = service.getService(sService);
        boolean actual = false;
        if(getService.getId().equals(sService.getId())) {
            actual = true;
        }
        Assert.assertTrue(actual);
    }
}