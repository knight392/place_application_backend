package com.place_application.demo.dao;


import com.place_application.demo.pojo.ReturnRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 补正打回持久层操作
 */
@Mapper
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
