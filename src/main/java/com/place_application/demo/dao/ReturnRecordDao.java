package com.place_application.demo.dao;


import com.place_application.demo.pojo.ReturnRecord;

/**
 * 补正打回持久层操作
 */
public interface ReturnRecordDao {
    /**
     * 增加补正打回
     */
    public  void insertReturnRecord(ReturnRecord returnRecord);


    /**
     * 查询补正打回
     */
    public ReturnRecord selectReturnRecordByNo(Integer return_no);

}
