package com.place_application.demo.service.impl;

import com.place_application.demo.dao.PlaceApplicationDao;
import com.place_application.demo.dao.PlaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.Place;
import com.place_application.demo.service.PlaceService;

import java.util.List;

/**
 * 场地管理服务
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private PlaceApplicationDao placeApplicationDao;

    @Override
    public Place addPlace(Place place) {
       int res = this.placeDao.insertPlace(place);
       if (res > 0) {
           place = this.placeDao.selectPlaceByNoOrName(place);
           return place;
       }
       return null;
    }

    @Override
    public boolean updatePlace(Place place) {
        int res = this.placeDao.updatePlace(place);
        return res > 0;
    }

    @Override
    public boolean deletePlace(Integer place_no) {
        // 中断流程


        int res = this.placeDao.deletePlace(place_no);
        return res > 0;
    }

    @Override
    public Place selectPlace(Place place){
        return this.placeDao.selectPlaceByNoOrName(place);
    }

    @Override
    public List<Place> selectAllPlaces() {
        List<Place> places = this.placeDao.selectAllPlaces();
        return places;
    }

    @Override
    public boolean isPlaceDuplicate(String place_name) {
        Place place = new Place();
        place.setPlace_name(place_name);
        Place placeTarget = this.placeDao.selectPlaceByNoOrName(place);
        return placeTarget != null;
    }

    @Override
    public List<Place> getPlacesWithoutProcedure() {
        return this.placeDao.selectPlacesWithoutProcedure();
    }

    @Override
    public List<Place> getPlacesAvailable() {
        return this.placeDao.selectPlacesAvailable();
    }
}
