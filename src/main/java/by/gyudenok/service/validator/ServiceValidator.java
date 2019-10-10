package by.gyudenok.service.validator;

import by.gyudenok.exception.ValidatorException;

public class ServiceValidator<T> {
    public boolean checkNull(T obj) throws ValidatorException {
        if(obj == null) {
            throw new ValidatorException("Argument is null");
        }
        return true;
    }
}
