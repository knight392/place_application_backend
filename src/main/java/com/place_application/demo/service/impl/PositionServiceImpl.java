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
        // 根据position_no,找出pro_order 大的 并 pro_no相同的 记录
        List<Integer> position_nos = this.positionDao.selectPositionsByNoAndPro_noAndPro_order(position_no);
        // 将 order - 1
        if(position_nos.size() != 0){
            this.positionDao.reduceOrderByNos(position_nos);
        }

        // 如果有关系到某个流程，则中断申请表

        int res = this.positionDao.deletePosition(position_no);
        return res > 0;
    }


    @Override
    public boolean updatePosition(Position position)  {
        // 如果移出了老师，则中断申请表
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
}
