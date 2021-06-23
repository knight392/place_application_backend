package com.place_application.demo.dao;

import com.place_application.demo.pojo.PlaceApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 申请表持久层操作
 */
@Mapper
public interface PlaceApplicationDao {
    /**
     * 添加申请表
     */
    public int insertPlaceApplication(PlaceApplication placeApplication);

    /**
     * 查询申请表
     */
    public PlaceApplication selectPlaceApplicationByNo(Integer apl_no);

    /**
     * 修改申请表
     */
    public int updatePlaceApplication(PlaceApplication placeApplication);

    /**
     * 根据pro_no，将 pro_no 设置为空， 删除流程使用
     */
    public int clearPro_noByPro_no(Integer pro_no);

    /**
     * 根据place_no，将 place_no 设置为空, 删除场地使用，或场地移出某申请流程时使用
     */
    public int clearPlace_noByPlace_no(Integer place_no);

    /**
     * 根据 place_no查找申请表
     */
    public List<PlaceApplication> selectPlaceApplicationByPlace_no(Integer place_no);

    /**
     * 根据 pro_no 查找申请表
     */
    public List<PlaceApplication> selectPlaceApplicationByPro_no(Integer pro_no);

    /**
     * 根据 student_no 找到 所属的申请表，并且根据提交时间从最新的日期排列
     */
    public List<PlaceApplication> getApplicationsByS_no(String s_no);
}
