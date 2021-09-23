package com.place_application.demo.dao;

import com.place_application.demo.pojo.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageDao {
    /**
     * 添加图片
     */
    public int insertImage(Image image);

    /**
     * 根据type查找Img
     */
    public List<Image> selectImagesByType(String type);
}
