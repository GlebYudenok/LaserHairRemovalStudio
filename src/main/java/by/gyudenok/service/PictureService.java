package by.gyudenok.service;

import by.gyudenok.exception.ServiceException;

public interface PictureService<Picture> {
    boolean addPictureToGalery(Picture picture) throws ServiceException;
    boolean deletePicture(String id) throws ServiceException;
}
