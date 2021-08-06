package com.place_application.demo.service.impl;

import com.place_application.demo.dao.ImageDao;
import com.place_application.demo.pojo.Image;
import com.place_application.demo.service.ImageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageDao imageDao;
    @Override
    public boolean addImage(Image image) {
        return imageDao.insertImage(image) > 0;
    }

    @Override
    public List<Image> getImagesByType(String type) {
        return imageDao.selectImagesByType(type);
    }
}
