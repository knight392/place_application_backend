package com.place_application.demo.service;


import com.place_application.demo.pojo.PlaceApplication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 场地申请表服务
 */
@Service
public interface PlaceApplicationService {
    /**
     * 添加申请表
     */
    public boolean addPlaceApplication(PlaceApplication placeApplication);

    /**
     * 修改申请表
     */
    public boolean updatePlaceApplication(PlaceApplication placeApplication);

    /**
     * 查询申请表
     */
    public PlaceApplication selectPlaceApplication(Integer apl_no);

    /**
     * 申请完成
     */
    public boolean finishApplication(Integer apl_no);

    /**
     * 申请打回
     */
    public boolean returnApplication(Integer apl_no);

    /**
     * 申请拒绝
     */
    public boolean refuseApplication(Integer apl_no);

    /**
     * 中断申请
     */
    public boolean breakOffApplication(Integer apl_no);


    /**
     * 申请进度+1
     */
    public boolean updateStep(Integer apl_no);

    /**
     * 重置申请进度, 可以被打回时调用
     */
    public boolean resetStep(Integer apl_no);

    /**
     * 根据学生学号找到所属的所有的申请表
     */
    public List<PlaceApplication> getApplicationsByStudent_no(String student_no);
}
