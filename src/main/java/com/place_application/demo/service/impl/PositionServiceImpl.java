package com.place_application.demo.service.impl;

import com.place_application.demo.dao.PositionDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.Position;
import com.place_application.demo.service.PositionService;

import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao positionDao;

    @Override
    public Position addPosition(Position position)  {
        int res = this.positionDao.insertPosition(position);
        System.out.println(res);
        if (res > 0) {
            position = this.positionDao.selectPositionByNoOrName(position);
            return position;
        }
        return null;
    }

    @Override
    public boolean deletePosition(Integer position_no) {
        // 如果有关系到某个流程，则中断申请表
        Integer pro_no = this.positionDao.getPro_noByPosition_no(position_no);
        String teacher_no = this.positionDao.getTeacher_noByPosition_no(position_no);
        System.out.println(pro_no);
        if(pro_no == null && teacher_no == null){
            int res = this.positionDao.deletePosition(position_no);
            return res > 0;
        }else{
            return false;
        }
    }


    @Override
    public boolean updatePosition(Position position)  {
        // 如果判断是否有流程，有则不能往下操作
        Integer pro_no = this.positionDao.getPro_noByPosition_no(position.getPosition_no());
        if(pro_no != null){
            return false;
        }

        if(position.getTeacher() == null) {
            this.positionDao.clearTeacherByNo(position.getPosition_no());
        }
        int res = this.positionDao.updatePosition(position);
        return res > 0;
    }

    @Override
    public List<Position> selectAllPositions() {
        return this.positionDao.selectAllPositions();
    }

    @Override
    public List<Position> selectPositionsByPro_no(Integer pro_no)  {
        return this.positionDao.selectPositionsByPro_no(pro_no);
    }

    // 避免脏读
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean isPositionDuplicate(String position_name) {
        Position position = new Position();
        position.setPosition_name(position_name);
        position = this.positionDao.selectPositionByNoOrName(position);
        return position != null;
    }

    @Override
    public Position findPosition(Position position)  {
        return this.positionDao.selectPositionByNoOrName(position);
    }

    @Override
    public boolean removeTeacher(Integer position_no) throws Exception {
        int res =  this.positionDao.clearTeacherByNo(position_no);
        return res > 0;
    }

    @Override
    public List<Position> selectPositionsWithoutProcedure() throws Exception {
        return this.positionDao.selectPositionsWithoutProcedure();
    }

    @Override
    public List<Position> findPositionsByTeacher_no(String teacher_no) {
        return this.positionDao.selectPositionsByTeacher_no(teacher_no);
    }
}
