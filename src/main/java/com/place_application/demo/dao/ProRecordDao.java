package com.place_application.demo.dao;


import com.place_application.demo.pojo.ProRecord;

/**
 * 审批过程记录持久层操作
 */
public interface ProRecordDao {
    /**
     * 添加审核记录
     */
    public void insertProRecord(ProRecord proRecord);
    /**
     * 查询审核记录
     * @return
     */
    public ProRecord selectProRecord(Integer apl_no);

}
