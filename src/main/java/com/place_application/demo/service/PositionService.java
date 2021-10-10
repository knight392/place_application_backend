package com.place_application.demo.service;

import com.place_application.demo.pojo.Position;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位管理服务
 */
@Service
public interface PositionService {
    /**
     * 添加职位
     */
    public Position addPosition(Position position) throws Exception;

    /**
     * 删除职位
     */
    public boolean deletePosition(Integer position_no)  throws Exception;

    /**
     * 更改职位信息
     */
    public boolean updatePosition(Position position)  throws Exception;

    /**
     * 查询所有职位
     */
    public List<Position> selectAllPositions()  throws Exception;

    /**
     * 根据pro_no查询所有的职位，并且职位按照pro_order从小到到排列
     */
    public List<Position> selectPositionsByPro_no(Integer pro_no)  throws Exception;

    /**
     * 判断职位名是否重复
     */
    public boolean isPositionDuplicate(String position_name) throws Exception;

    /**
     * 查询职位
     */
    public Position findPosition(Position position) throws Exception;

    /**
     * 移出教师职位
     */
    public boolean removeTeacher(Integer position_no) throws Exception;

    /**
     * 查询没有流程的所有的职位
     */
    public List<Position> selectPositionsWithoutProcedure() throws Exception;

    /**
     * 根据教师账号找职位
     * @param teacher_no
     * @return
     */
    public List<Position> findPositionsByTeacher_no(String teacher_no);

}
