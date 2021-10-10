package com.place_application.demo.service.impl;

import com.place_application.demo.dao.AplRecordDao;
import com.place_application.demo.dao.PlaceApplicationDao;
import com.place_application.demo.pojo.AplRecord;
import com.place_application.demo.pojo.Task;
import com.place_application.demo.pojo.Teacher;
import com.place_application.demo.service.TaskService;
import com.place_application.demo.service.TeacherService;
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
    @Autowired
    private TaskService taskService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AplRecordDao aplRecordDao;

    @Override
    public boolean addPlaceApplication(PlaceApplication placeApplication) {
        // 初始化 cur_step 1 已提交
        placeApplication.setCur_step(1);
        // cur_status 1 申请中
        placeApplication.setCur_status(1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss");
        Date date = new Date();
        placeApplication.setApl_date(df.format(date));

        // 添加申请表
        int res = this.placeApplicationDao.insertPlaceApplication(placeApplication);


        int  pro_no = placeApplication.getAplProcedure().getPro_no();
        Teacher teacher = this.teacherService.getTeacherInProcedure(pro_no, 1); // 找流程中的第1个教师

        // 添加审批任务
        int res2 = this.taskService.addTask(df.format(date), placeApplication,teacher.getTeacher_no(), 0 );

        // 添加申请记录
        AplRecord aplRecord = new AplRecord();
        aplRecord.setApl_no(placeApplication.getApl_no());
        aplRecord.setStatus_time(df.format(date));
        aplRecord.setStatus_info("申请提交成功");
        this.aplRecordDao.insertAplRecord(aplRecord);

        return res > 0;
    }

    @Override
    public boolean updatePlaceApplication(PlaceApplication placeApplication) {

        // step 改成 申请补正成功（和提交成功一样）
        placeApplication.setCur_step(1);
        // status 改成 申请中
        placeApplication.setCur_status(1);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss");
        placeApplication.setApl_date(df.format(date)); // 更新提交时间，方便展示最新操作的申请表
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        // 清空 refuse_reason
        this.placeApplicationDao.clearRefuseReason(placeApplication.getApl_no());

        // 产生再审记录
        AplRecord aplRecord = new AplRecord();
        aplRecord.setApl_no(placeApplication.getApl_no());

        aplRecord.setStatus_time(df.format(date));
        aplRecord.setStatus_info("申请补正并提交成功");
        this.aplRecordDao.insertAplRecord(aplRecord);

        // 生成再审任务
        int  pro_no = placeApplication.getAplProcedure().getPro_no();
        Teacher teacher = this.teacherService.getTeacherInProcedure(pro_no, 1); // 找流程中的第1个教师
        this.taskService.addTask(df.format(date),placeApplication, teacher.getTeacher_no(), 1);
        return res > 0;
    }

    @Override
    public List<Integer> getApplicationsByPro_no(int pro_no) {
        return this.placeApplicationDao.selectPlaceApplicationsByPro_no(pro_no);
    }



    @Override
    public boolean finishApplication(Integer apl_no) throws Exception{
        // 将cur_status 设置为 2 - 通过
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_status(2);
        boolean res1 = this.placeApplicationDao.updatePlaceApplication(placeApplication) > 0;
        boolean res2 = this.updateStep(apl_no); // cur_step + 1

        // 产生申请成功记录
        AplRecord aplRecord = new AplRecord();
        aplRecord.setApl_no(apl_no);
        Date date = new Date();
        date = new Date(date.getTime() + 1000); // 比上一条记录多加1秒
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss");
        aplRecord.setStatus_time(df.format(date));
        aplRecord.setStatus_info("申请成功");
        boolean res3 =  this.aplRecordDao.insertAplRecord(aplRecord) > 0;
        if(!res1 || !res2 || ! res3){
            throw new Exception("完成失败");
        }
        return true;
    }

    @Override
    public boolean rewriteApplication(Integer apl_no, String reason) {
        // 将cur_status 设置为 3 - 打回
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_status(3);
        placeApplication.setRefuse_reason(reason);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);
        // 重置步骤
        return res > 0 && this.resetStep(apl_no);
    }

    @Override
    public boolean refuseApplication(Integer apl_no, String reason) {
        // 将cur_status 设置为 4 - 拒接
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_status(4);
        placeApplication.setRefuse_reason(reason);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);

        AplRecord aplRecord = new AplRecord();
        aplRecord.setApl_no(apl_no);
        Date date = new Date();
        date = new Date(date.getTime() + 1000); // 比上一条记录多加1秒
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss");
        aplRecord.setStatus_time(df.format(date));
        aplRecord.setStatus_info("申请失败");
        this.aplRecordDao.insertAplRecord(aplRecord);

        return res > 0;
    }

    @Override
    public boolean breakOffApplication(Integer apl_no) {
        // 将cur_status 设置为 5 - 中断
        PlaceApplication placeApplication = new PlaceApplication();
        placeApplication.setApl_no(apl_no);
        placeApplication.setCur_status(5);
        int res = this.placeApplicationDao.updatePlaceApplication(placeApplication);

        AplRecord aplRecord = new AplRecord();
        aplRecord.setApl_no(apl_no);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss");
        aplRecord.setStatus_time(df.format(date));
        aplRecord.setStatus_info("申请中断");
        this.aplRecordDao.insertAplRecord(aplRecord);

        return res > 0;
    }

    @Override
    public boolean updateStep(Integer apl_no) {
        PlaceApplication placeApplication = this.placeApplicationDao.selectPlaceApplicationByNo(apl_no);
        int next_step = placeApplication.getCur_step() + 1;

        PlaceApplication newPlaceApplication = new PlaceApplication();
        newPlaceApplication.setApl_no(apl_no);
        newPlaceApplication.setCur_step(next_step);

        if(next_step > placeApplication.getCur_step()){
            // 更新最大 step
            newPlaceApplication.setMax_step(next_step);
        }
        int res = this.placeApplicationDao.updatePlaceApplication(newPlaceApplication);
        return res > 0;
    }

    @Override
    public boolean resetStep(Integer apl_no) {
        // 将cur_step 步骤 变成 0
        return this.placeApplicationDao.resetStep(apl_no) > 0;
    }

    @Override
    public List<PlaceApplication> getApplicationsByStudent_no(String s_no) {
        return this.placeApplicationDao.getApplicationsByS_no(s_no);
    }

    @Override
    public Integer copyApplication(Integer apl_no) {
        PlaceApplication placeApplication = this.placeApplicationDao.selectPlaceApplicationByNo(apl_no);
        PlaceApplication copyPlaceApplication = this.deepCopy(placeApplication);
        this.placeApplicationDao.insertPlaceApplication(copyPlaceApplication); // 插入后返回自增主键 apl_no
        return copyPlaceApplication.getApl_no();
    }

    /**
     * 深拷贝
     * @param placeApplication
     * @return
     */
    private PlaceApplication deepCopy(PlaceApplication placeApplication){
        PlaceApplication copyPlaceApplication = new PlaceApplication();
        copyPlaceApplication.setPlace(placeApplication.getPlace());
        copyPlaceApplication.setApl_date(placeApplication.getApl_date());
        copyPlaceApplication.setCur_status(placeApplication.getCur_status());
        copyPlaceApplication.setCur_step(placeApplication.getCur_step());
        copyPlaceApplication.setAplProcedure(placeApplication.getAplProcedure());
        copyPlaceApplication.setBegin_time(placeApplication.getBegin_time());
        copyPlaceApplication.setEnd_time(placeApplication.getEnd_time());
        copyPlaceApplication.setTutor_name(placeApplication.getTutor_name());
        copyPlaceApplication.setStudent(placeApplication.getStudent());
        copyPlaceApplication.setOrg_name(placeApplication.getOrg_name());
        copyPlaceApplication.setS_phone(placeApplication.getS_phone());
        copyPlaceApplication.setPurpose(placeApplication.getPurpose());
        copyPlaceApplication.setIs_copy(1);
        return copyPlaceApplication;
    }



}
