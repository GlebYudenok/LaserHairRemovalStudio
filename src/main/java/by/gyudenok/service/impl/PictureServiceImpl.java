package by.gyudenok.service.impl;

import by.gyudenok.dao.PictureDao;
import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Picture;
import by.gyudenok.exception.DaoException;
import by.gyudenok.exception.ServiceException;
import by.gyudenok.exception.ValidatorException;
import by.gyudenok.service.PictureService;
import by.gyudenok.service.validator.PictureValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PictureServiceImpl implements PictureService<Picture> {

    private static final Logger LOGGER = LogManager.getLogger(PictureServiceImpl.class);
    private static final SqlDaoFactory factory = SqlDaoFactory.getInstance();
    private static final PictureDao<Picture> pictureDao = factory.getSqlPictureDao();
    private static final PictureValidator validator = new PictureValidator();

    @Override
    public boolean addPictureToGalery(Picture picture) throws ServiceException {
        try {
            validator.validateNullPicture(picture);
            validator.validateNull(picture.getLink());
            validator.validateNull(picture.getId());
            pictureDao.create(picture);
        }catch (ValidatorException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        LOGGER.info("picture was added successfully");
        return true;
    }

    @Override
    public boolean deletePicture(String id) throws ServiceException {
        try {
            validator.validateNull(id);
            pictureDao.delete(id);
        }catch (ValidatorException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return true;
    }
}
