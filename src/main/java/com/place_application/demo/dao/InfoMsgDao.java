package com.place_application.demo.dao;


import com.place_application.demo.pojo.InfoMsg;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息通知持久层操作
 */
@Mapper
public interface InfoMsgDao {

    /**
     * 增加消息通知
     */
    public  void insertInfoMsg(InfoMsg infoMsg);
    /**
     * 查询消息通知
     */
    public InfoMsg selectInfoMsgByNo(Integer info_no);
}
