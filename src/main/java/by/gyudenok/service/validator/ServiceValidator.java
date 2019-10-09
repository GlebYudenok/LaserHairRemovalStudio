package by.gyudenok.service.validator;

import by.gyudenok.entity.Service;
import by.gyudenok.exception.ValidatorException;

public class ServiceValidator {
    public boolean checkServiceNull(Service service) throws ValidatorException {
        if(service == null) {
            throw new ValidatorException("Argument is null");
        }
        return true;
    }

    public boolean checkIdNull(String id) throws ValidatorException {
        if(id == null) {
            throw new ValidatorException("Id is null");
        }
        return true;
    }
}
