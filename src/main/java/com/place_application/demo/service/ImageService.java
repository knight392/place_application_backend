package com.place_application.demo.service;

import com.place_application.demo.pojo.Image;

import java.util.List;

public interface ImageService {
    /**
     * 添加图片
     */
    public boolean addImage(Image image);

    /**
     * 根据type找images
     */
    public List<Image> getImagesByType(String type);
}
