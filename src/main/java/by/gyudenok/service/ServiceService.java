package by.gyudenok.service;

import by.gyudenok.exception.ServiceException;

import java.util.List;

public interface ServiceService<Service> {
    List<Service> getServicesList() throws ServiceException;
    boolean createService(Service service) throws ServiceException;
    boolean editService(Service service) throws ServiceException;
    boolean deleteService(String serviceId) throws ServiceException;
    Service getService(Service service) throws ServiceException;
}
