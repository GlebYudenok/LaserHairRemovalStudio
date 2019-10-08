package by.gyudenok.service.factory;

import by.gyudenok.service.*;
import by.gyudenok.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private static final AppointmentService sAppointmentService = new AppointmentServiceImpl();
    private static final ComplexServiceService sComplexService = new ComplexServiceServiceImpl();
    private static final ServiceService sServiceService = new ServiceServiceImpl();
    private static final UserService sUserService = new UserServiceImpl();
    private static final PictureService sPictureService = new PictureServiceImpl();

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public AppointmentService getsAppointmentService() {
        return sAppointmentService;
    }

    public ComplexServiceService getsComplexService() {
        return sComplexService;
    }

    public ServiceService getsServiceService() {
        return sServiceService;
    }

    public UserService getsUserService() {
        return sUserService;
    }

    public PictureService getsPictureService() {
        return sPictureService;
    }
}
