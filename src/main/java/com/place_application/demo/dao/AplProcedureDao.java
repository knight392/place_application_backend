package com.place_application.demo.dao;

import com.place_application.demo.pojo.AplProcedure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 场地申请流程持久层操作
 */
@Mapper
public interface AplProcedureDao {
    /**
     * 修改流程
     */
    public int updateAplProcedure(AplProcedure aplProcedure);

    /**
     * 添加流程
     */
    public int insertAplProcedure(AplProcedure aplProcedure);

    /**
     * 删除流程
     */
    public int deleteAplProcedure(Integer pro_no);

    /**
     * 查询所有的流程
     */
    public List<AplProcedure> selectAllAplProcedures();

    /**
     * 查询流程
     */
    public AplProcedure selectAplProcedureByNoOrNameOrFormName(AplProcedure aplProcedure);

}
