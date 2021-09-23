package com.place_application.demo.service;

import com.place_application.demo.pojo.Place;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 场地服务
 */
@Service
public interface PlaceService {
    /**
     * 添加场地
     */
    public Place addPlace(Place place);

    /**
     * 修改场地
     */
    public boolean updatePlace(Place place);

    /**
     * 删除场地
     */
    public boolean deletePlace(Integer place_no);

    /**
     * 查询场地
     */
    public Place selectPlace(Place place);

    /**
     * 查询所有的场地
     */
    public List<Place> selectAllPlaces();

    /**
     * 判断场地名是否重复
     */
    public boolean isPlaceDuplicate(String place_name);


    /**
     * 获取没有流程的场地
     */
    public List<Place> getPlacesWithoutProcedure();

    /**
     * 获取可申请的场地
     */

    public List<Place> getPlacesAvailable();
}
