package com.place_application.demo.service;

import com.place_application.demo.pojo.AplProcedure;
import com.place_application.demo.pojo.Position;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请流程服务
 */
@Service
public interface AplProcedureService {
    /**
     * 添加流程
     */
    public AplProcedure addAplProcedure(AplProcedure aplProcedure);

    /**
     * 删除流程
     */
    public boolean deleteAplProcedure(Integer pro_no);

    /**
     * 查询所有流程
     */
    public List<AplProcedure> selectAllAplProcedures();

    /**
     * 判断流程名或对象申请表名是否重复
     */
    public boolean isAplProcedureDuplicate(AplProcedure aplProcedure);

    /**
     * 查询流程
     */
    public AplProcedure selectAplProcedure(Integer pro_no);

    /**
     * 修改流程名字和申请表名字
     */
    public boolean updateAplProcedure(AplProcedure aplProcedure);

    /**
     * 修改流程包含的场地
     * @param pro_no
     * @param place_nos 新修改的场地编码
     * @return
     */
    public boolean updatePlaces(Integer pro_no, List<Integer> place_nos);

    /**
     * 修改流程中包含的职位数量以及步骤
     * @param pro_no
     * @param positions 新修改的职位对象，主要需要的是position_no 和 pro_order
     * @return
     */
    public boolean updatePositions(Integer pro_no, List<Position> positions);

    /**
     * 添加场地到流程中
     */
    boolean addPlacesToProcedure(Integer pro_no, List<Integer> place_nos);

    /**
     * 从流程中移出场地
     */
    boolean removePlacesFromProcedure(List<Integer> place_nos);

}
