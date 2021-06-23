package com.place_application.demo.service.impl;

import com.place_application.demo.dao.PlaceApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.PlaceApplication;
import com.place_application.demo.service.PlaceApplicationService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PlaceApplicationServiceImpl implements PlaceApplicationService {
    @Autowired
    private PlaceApplicationDao placeApplicationDao;

    @Override
    public boolean addPlaceApplication(PlaceApplication placeApplication) {
        // 初始化 cur_step 1 已提交
        placeApplication.setCur_step(1);
        // cur_status 1 申请中
        placeApplication.setCur_status(1);
        // apl_date 设置系统为提交日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        placeApplication.setApl_date(df.format(date));

        int res = this.placeApplicationDao.insertPlaceApplication(placeApplication);

        return res > 0;
    }

    @Override
    public boolean updatePlaceApplication(PlaceApplication placeApplication) {
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        return res > 0;
    }

    @Override
    public PlaceApplication selectPlaceApplication(Integer apl_no) {
        return this.placeApplicationDao.selectPlaceApplicationByNo(apl_no);
    }

    @Override
    public boolean finishApplication(Integer apl_no) {
        // 将cur_status 设置为 2 - 通过
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_status(2);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        return res > 0;
    }

    @Override
    public boolean returnApplication(Integer apl_no) {
        // 将cur_status 设置为 3 - 打回
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_status(3);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        // 重置步骤
        return res > 0 && this.resetStep(apl_no);
    }

    @Override
    public boolean refuseApplication(Integer apl_no) {
        // 将cur_status 设置为 4 - 拒接
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_status(4);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        return res > 0;
    }

    @Override
    public boolean breakOffApplication(Integer apl_no) {
        // 将cur_status 设置为 5 - 中断
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_status(5);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        return res > 0;
    }

    @Override
    public boolean updateStep(Integer apl_no) {
        // 将cur_step 步骤 + 1
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        int cur_step = this.placeApplicationDao.selectPlaceApplicationByNo(apl_no).getCur_step();
        placeApplication.setCur_step(cur_step + 1);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        return res > 0;
    }

    @Override
    public boolean resetStep(Integer apl_no) {
        // 将cur_step 步骤 变成 0
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_step(0);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        return res > 0;
    }

    @Override
    public List<PlaceApplication> getApplicationsByStudent_no(String s_no) {
        return this.placeApplicationDao.getApplicationsByS_no(s_no);
    }
}
