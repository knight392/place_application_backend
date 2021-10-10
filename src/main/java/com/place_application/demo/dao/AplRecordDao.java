package com.place_application.demo.dao;


import com.place_application.demo.pojo.AplRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 审批过程记录持久层操作
 */
@Mapper
public interface AplRecordDao {
    /**
     * 添加审核记录
     */
    public int insertAplRecord(AplRecord aplRecord);
    /**
     * 查询审核记录
     * @return
     */
    public List<AplRecord> selectAplRecords(Integer apl_no);
}
