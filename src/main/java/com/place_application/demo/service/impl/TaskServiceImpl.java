package com.place_application.demo.service.impl;


import com.place_application.demo.dao.AplRecordDao;
import com.place_application.demo.dao.PlaceApplicationDao;
import com.place_application.demo.dao.PositionDao;
import com.place_application.demo.dao.TaskDao;
import com.place_application.demo.pojo.*;
import com.place_application.demo.service.PlaceApplicationService;
import com.place_application.demo.service.TaskService;
import com.place_application.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private PlaceApplicationService placeApplicationService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AplRecordDao aplRecordDao;
    @Autowired
    private PlaceApplicationDao placeApplicationDao;



    @Override
    public int addTask(String now, PlaceApplication placeApplication, String teacher_no, int is_reSentence) {
        Task task = new Task();
        task.setIs_finished(0); // 完成状态为 未完成
        task.setGenerate_time(now); // 任务产生时间
        task.setPlaceApplication(placeApplication);
        task.setIs_reSentence(is_reSentence);
        task.setTeacher_no(teacher_no);
        return this.taskDao.insertTask(task);
    }

    @Override
    public List<Task> getTodoTasks(String teacher_no, Integer task_no) {
        List<Task> taskList = this.taskDao.selectTodoTasks(teacher_no);
        System.out.println(taskList.size());
        // 从 第 rank 个任务开始往后找5个
        return cutOut(taskList, task_no);
    }

    @Override
    public List<Task> getFinishedTasks(String teacher_no, Integer task_no) {
        List<Task> taskList = this.taskDao.selectFinishedTasks(teacher_no);
        // 从 第 rank 个任务开始往后找5个
        return cutOut(taskList, task_no);
    }

    @Override
    @Transactional
    public Boolean sentenceTask(SentenceRequest sentenceRequest) throws Exception{
        // 参数没有传正确
        if(sentenceRequest.getTask_no() == 0 || sentenceRequest.getDecision() == 0 || ( (sentenceRequest.getDecision() == 2 || sentenceRequest.getDecision() == 3) && sentenceRequest.getRefuseReason() == null)){
            return false;
        }

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss");
        String curTeacherName = this.taskDao.getTeacherNameByNo(sentenceRequest.getTask_no());
        Task oldTask = this.taskDao.selectTaskByNo(sentenceRequest.getTask_no());

        // 通过
        if(sentenceRequest.getDecision() == 1){
            return approve(oldTask, df.format(date), curTeacherName);
        }
        // 打回
        else if(sentenceRequest.getDecision() == 2){
            return rewrite(oldTask, sentenceRequest.getRefuseReason(),df.format(date),curTeacherName);
        }
        // 拒绝
        else if(sentenceRequest.getDecision() == 3){
            return refuse(oldTask, sentenceRequest.getRefuseReason(),df.format(date), curTeacherName);
        }


        return false;
    }

    @Override
    public Boolean breakOffTasks(List<Integer> aplList) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss");
        List<Task> taskList = new ArrayList<>();
        boolean res = true;
        for(Integer apl_no : aplList){
            Task task = new Task();
            PlaceApplication placeApplication = new PlaceApplication();
            placeApplication.setApl_no(apl_no);
            task.setPlaceApplication(placeApplication);
            task.setFinished_time(df.format(date));
            res = res && (this.taskDao.breakOffTask(task) > 0);
        }
        return res ;
    }

    /**
     * 从数组的 task_no 的下标 开始往后至多截取 5 个
     * @param taskList
     * @param task_no
     * @return
     */
    private List<Task> cutOut(List<Task> taskList, Integer task_no){
        int rank = 0;
        // 找下标
        for(int i = 0; task_no != 0 && i < taskList.size(); i ++){
            if(taskList.get(i).getTask_no() == task_no){
                rank = i + 1;
                break;
            }
        }
        List<Task> res = new ArrayList<>();
        for(int i = rank; i < taskList.size() && i < rank + 5; i ++){
            res.add(taskList.get(i));
        }
        return res;
    }

    /**
     * 通过
     * @param oldTask
     * @param now
     * @param curTeacherName
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean approve(Task oldTask, String now, String curTeacherName) throws Exception{
        // updateTask
        // 修改任务 is_finished = 1
        // 设置完成时间为当前时间
        Task task = new Task();
        task.setTask_no(oldTask.getTask_no());
        task.setIs_finished(1);
        task.setFinished_time(now);
        boolean res1 = this.taskDao.updateTask(task) > 0;


        // 产生当前老师审批通过总记录
        AplRecord aplRecord = new AplRecord();
        aplRecord.setApl_no(oldTask.getPlaceApplication().getApl_no());
        aplRecord.setStatus_time(now);

        if(oldTask.getIs_reSentence() == 0){
            // 第一次审批
            aplRecord.setStatus_info(curTeacherName + "审批通过");
        }else{
            // 再审
            aplRecord.setStatus_info(curTeacherName + "再审通过");
        }
        this.aplRecordDao.insertAplRecord(aplRecord);

        // placeApplicationService.updateStep


        // 查找下一个审批老师
        boolean res2 = false;

        Teacher teacherNext = this.teacherService.getTeacherInProcedure(oldTask.getPlaceApplication().getAplProcedure().getPro_no(), (oldTask.getPlaceApplication().getCur_step() + 1)); // 找流程中下一位老师

        if(teacherNext != null){
            // 有，产生新的任务
            // 获取更新后的 max_step
            int max_step = this.placeApplicationDao.getMaxStepByNo(oldTask.getPlaceApplication().getApl_no());
            int is_reSentence = max_step >= (oldTask.getPlaceApplication().getCur_step() + 1) ? 1 : 0;

            res2 = this.addTask(now, oldTask.getPlaceApplication(), teacherNext.getTeacher_no(), is_reSentence) > 0;
        }else{
            // 无，则审批以完成状态结束
            res2 = this.placeApplicationService.finishApplication(oldTask.getPlaceApplication().getApl_no());
        }

        // 修改 application 的 cur_step + 1, 必须先找下一个教师再新 curStep
        boolean res3 =  placeApplicationService.updateStep(oldTask.getPlaceApplication().getApl_no());

        if(!res1 || !res2 || !res3){
            throw new Exception("通过失败, res1:" + res1 + ",res2:" + res2 + ", res3: " + res3 );
        }
        return true;
    }

    /**
     * 打回
     * @param oldTask
     * @param reason
     * @param now
     * @param curTeacherName
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean rewrite(Task oldTask, String reason, String now, String curTeacherName) throws Exception{
        // 修改 is_finished 为 2 打回
        // 写入 审批时间
        // 写入 打回理由

        Task task = new Task();
        task.setTask_no(oldTask.getTask_no());
        task.setFinished_time(now);
        task.setIs_finished(2);
        task.setRefuse_reason(reason);


        boolean res1 =  this.taskDao.updateTask(task) > 0;

        // copy 申请表内容，修改所有之前审批过该审批表的 apl_no 为 copy 的
        int copy_apl_no =  this.placeApplicationService.copyApplication(oldTask.getPlaceApplication().getApl_no());
        this.alterApl_no(oldTask.getPlaceApplication().getApl_no(), copy_apl_no);

        // 产生打回的总记录
        AplRecord aplRecord = new AplRecord();
        aplRecord.setApl_no(oldTask.getPlaceApplication().getApl_no());
        aplRecord.setStatus_time(now);
        if(oldTask.getIs_reSentence() == 0){
            aplRecord.setStatus_info(curTeacherName + "审批打回。打回原因：" + reason + ".");
        }else{
            aplRecord.setStatus_info(curTeacherName + "再审打回。打回原因：" + reason + ".");
        }
        boolean res2 = this.aplRecordDao.insertAplRecord(aplRecord) > 0;


        // 更新申请表状态
        boolean res3 = this.placeApplicationService.rewriteApplication(oldTask.getPlaceApplication().getApl_no(), reason);

        if(!res1 || !res2 || !res3){
            throw new Exception("打回失败, res1:" + res1 + ",res2:" + res2 + ", res3: " + res3 );
        }
        return true;
    }

    /**
     * 拒绝
     * @param oldTask
     * @param reason
     * @param now
     * @param curTeacherName
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean refuse(Task oldTask, String reason, String now, String curTeacherName) throws Exception{
        // 更新task is_finished = 3
        // 添加 finished_time
        // 添加 拒绝原因
        Task task = new Task();
        task.setTask_no(oldTask.getTask_no());
        task.setIs_finished(3);
        task.setFinished_time(now);
        task.setRefuse_reason(reason);
        boolean res1  =  this.taskDao.updateTask(task) > 0;

        // 产生教师拒绝记录
        AplRecord aplRecord = new AplRecord();
        aplRecord.setApl_no(oldTask.getPlaceApplication().getApl_no());
        aplRecord.setStatus_time(now);
        if(oldTask.getIs_reSentence() == 0){
            aplRecord.setStatus_info(curTeacherName + "审批拒绝。拒绝原因：" + reason + "。");
        }else{
            aplRecord.setStatus_info(curTeacherName + "再审拒绝。拒绝原因：" + reason + "。");
        }
        boolean res2 = this.aplRecordDao.insertAplRecord(aplRecord) > 0;


        // 更新申请表状态
        boolean res3 =  this.placeApplicationService.refuseApplication(oldTask.getPlaceApplication().getApl_no(), reason);
        if(!res1 || !res2 || !res3){
            throw new Exception("拒绝失败, res1:" + res1 + ",res2:" + res2 + ", res3: " + res3 );
        }
        return true;
    }


    /**
     * 批量修改 apl_no
     * @param apl_no_old
     * @param apl_no_new
     * @return
     */
    private boolean alterApl_no(int apl_no_old, int apl_no_new){
        // 找出 apl_no_old 的 task_no
        List<Integer> task_nos = this.taskDao.getTaskNosByAplNo(apl_no_old);
        // 修改 apl_no
        TaskAlterAplAssist alterAplAssist = new TaskAlterAplAssist();
        alterAplAssist.setApl_no(apl_no_new);
        alterAplAssist.setTask_nos(task_nos);
        return this.taskDao.alterApl_nos(alterAplAssist) > 0;
    }

}
