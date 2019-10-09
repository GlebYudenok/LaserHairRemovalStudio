package by.gyudenok.service.validator;

import by.gyudenok.entity.Picture;
import by.gyudenok.exception.ValidatorException;

public class PictureValidator {
    public boolean validateNull(String variable) throws ValidatorException {
        if(variable == null) {
            throw new ValidatorException("Variable is null");
        }
        return true;
    }

    public boolean validateNullPicture(Picture picture) throws ValidatorException {
        if(picture == null) {
            throw new ValidatorException("Argument is null");
        }
        return true;
    }
}
