package by.gyudenok.service.impl;

import by.gyudenok.entity.Picture;
import by.gyudenok.exception.ServiceException;
import by.gyudenok.service.PictureService;
import by.gyudenok.service.factory.ServiceFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PictureServiceImplTest {

    private static final ServiceFactory factory = ServiceFactory.getInstance();
    private static final PictureService<Picture> picService = factory.getsPictureService();
    private static Picture mPicture = null;

    @BeforeClass
    public static void init() {
        mPicture = new Picture("testId", "testLink");
    }

    @AfterClass
    public static void destroy() throws ServiceException {
        picService.deletePicture("testId");
    }

    @Test
    public void addPicture() throws ServiceException {
        boolean actual = picService.addPictureToGalery(mPicture);
        Assert.assertTrue(actual);
    }

    @Test
    public void deletePicture() throws ServiceException {
        addPicture();
        boolean actual = picService.deletePicture("testId");
        Assert.assertTrue(actual);
    }
}