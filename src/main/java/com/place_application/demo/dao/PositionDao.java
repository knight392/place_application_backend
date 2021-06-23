package com.place_application.demo.dao;

import com.place_application.demo.pojo.Position;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 职位持久层操作
 */
@Mapper
public interface PositionDao {
    /**
     * 添加职位
     */
    public int insertPosition(Position position);

    /**
     * 删除职位
     */
    public int deletePosition(Integer position_no);

    /**
     * 更新职位
     */
    public int updatePosition(Position position);

    /**
     * 查询所有职位
     */
    public List<Position> selectAllPositions();

    /**
     * 查询职位
     */
    public Position selectPositionByNoOrName(Position position);

    /**
     * 根据pro_no查询职位, 并且按 pro_order 从小到排列
     */
    public List<Position> selectPositionsByPro_no(Integer pro_no);


    /**
     * 根据pro_no 清空 pro_no 和 pro_order，批量清除流程中的所属场地
     */
    public int clearPro_noAndPro_orderByPro_no(Integer pro_no);

    /**
     * 根据position_no,找到比pro_order 大的 并 pro_no相同的 记录的
     */
    public List<Integer> selectPositionsByNoAndPro_noAndPro_order(Integer position_no);

    /**
     * 根据 position_no 将 pro_order -1
     */
    public int reduceOrderByNos(List<Integer> nos);

    /**
     * 根据 position_no 删除 pro_no
     */
    public int clearProcedureByNo(Integer position_no);

    /**
     * 根据 position_no 删除 teacher_no
     */
    public int clearTeacherByNo(Integer position_no);


    /**
     * 获取 没有流程的职位
     */
    public List<Position> selectPositionsWithoutProcedure();
}
