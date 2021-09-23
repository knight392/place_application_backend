package com.place_application.demo.service.impl;

import com.place_application.demo.dao.AplProcedureDao;
import com.place_application.demo.dao.PlaceDao;
import com.place_application.demo.dao.PositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.AplProcedure;
import com.place_application.demo.pojo.Place;
import com.place_application.demo.pojo.Position;
import com.place_application.demo.service.AplProcedureService;
import com.place_application.demo.utils.MySet;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AplProcedureServiceImpl implements AplProcedureService {
    @Autowired
    private AplProcedureDao aplProcedureDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private PositionDao positionDao;

    @Override
    public AplProcedure addAplProcedure(AplProcedure aplProcedure) {
       int res =  this.aplProcedureDao.insertAplProcedure(aplProcedure);
       if (res > 0) {
           return aplProcedure;
       }
       return null;
    }

    @Override
    public boolean deleteAplProcedure(Integer pro_no) {
        // 中断申请表

        int res = this.aplProcedureDao.deleteAplProcedure(pro_no);
        return res > 0;
    }

    @Override
    public List<AplProcedure> selectAllAplProcedures() {
        return this.aplProcedureDao.selectAllAplProcedures();
    }

    @Override
    public boolean isAplProcedureDuplicate(AplProcedure aplProcedure) {
        AplProcedure aplProcedureTarget = null;
        // 判断流程名是否重复
        if (aplProcedure.getPro_name() != null) {
            AplProcedure aplProcedureResource = new AplProcedure();
            aplProcedureResource.setPro_name(aplProcedure.getPro_name());
            aplProcedureTarget = this.aplProcedureDao.selectAplProcedureByNoOrNameOrFormName(aplProcedureResource);
            if (aplProcedureTarget != null) return true;
        }
        // 判断对象表名是否重复
        if (aplProcedure.getPro_form_name() != null) {
            AplProcedure aplProcedureResource = new AplProcedure();
            aplProcedureResource.setPro_form_name(aplProcedure.getPro_form_name());
            aplProcedureTarget = this.aplProcedureDao.selectAplProcedureByNoOrNameOrFormName(aplProcedureResource);
            if (aplProcedureTarget != null) return true;
        }
        return false;
    }

    @Override
    public AplProcedure selectAplProcedure(Integer pro_no) {
        AplProcedure aplProcedure = new AplProcedure();
        aplProcedure.setPro_no(pro_no);
        return this.aplProcedureDao.selectAplProcedureByNoOrNameOrFormName(aplProcedure);
    }

    @Override
    public boolean updateAplProcedure(AplProcedure aplProcedure) {
        // 更新流程名和对应的申请表名
        int res = this.aplProcedureDao.updateAplProcedure(aplProcedure);

        // 更新职位
        this.updatePositions(aplProcedure.getPro_no(), aplProcedure.getPositions());

        int available = aplProcedure.getPositions().size() > 0 ? 1 : 0;
        // 最新的场地编号
        List<Integer> new_places = new ArrayList<>();
        for(Place place : aplProcedure.getPlaces()) {
            new_places.add(place.getPlace_no());
        }
        // 更新场地
        this.updatePlaces(aplProcedure.getPro_no(), new_places, available);

        return res != 0;
    }

    @Override
    public boolean updatePlaces(Integer pro_no, List<Integer> new_places, int available) {
        List<Integer> old_places = this.placeDao.selectPlace_nosByPro_no(pro_no);

        // 找到被移出的场地,把这些场地设置为不可申请
        List<Integer> removed = new MySet().subtract(old_places,new_places);
        if (removed.size() != 0) {
            this.removePlacesFromProcedure(removed);
        }

        // 更新当前包含的场地的 pro_no 和 available
        this.updatePlacesInProcedure(pro_no, new_places, available);
        return true;
    }

    @Override
    public boolean updatePositions(Integer pro_no, List<Position> positions) {
        // 清除所属pro_no的职位中的pro_no 和 pro_order
        this.positionDao.clearPro_noAndPro_orderByPro_no(pro_no);

        // 重新添加属于 pro_no的职位
        for(Position position : positions) {
            AplProcedure aplProcedure = new AplProcedure();
            aplProcedure.setPro_no(pro_no);
            position.setAplProcedure(aplProcedure);
            this.positionDao.updatePosition(position);
        }
        return true;
    }

    @Override
    public boolean updatePlacesInProcedure(Integer pro_no, List<Integer> place_nos, int available) {
        for (Integer place_no : place_nos) {
            Place place = new Place();
            place.setPlace_no(place_no);
            place.setAvailable(available);
            AplProcedure aplProcedure = new AplProcedure();
            aplProcedure.setPro_no(pro_no);
            place.setAplProcedure(aplProcedure);
            this.placeDao.updatePlace(place);
        }
        return true;
    }

    @Override
    public boolean removePlacesFromProcedure(List<Integer> place_nos) {
        // 中断申请表
        int res = this.placeDao.removePro_noByPlace_no(place_nos);
        return res != 0;
    }
}
