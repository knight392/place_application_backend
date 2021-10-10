package com.place_application.demo.dao;


import com.place_application.demo.pojo.Task;
import com.place_application.demo.pojo.TaskAlterAplAssist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 任务审批持久层操作
 */
@Mapper
public interface TaskDao {
    /**
     * 添加审批任务
     */
    public int insertTask(Task task);


    /**
     *查找教师待审批任务
     * @param teacher_no
     * @return
     */
    public List<Task> selectTodoTasks(String teacher_no);

    /**
     * 查到教师已审批完的任务
     * @param teacher_no
     * @return
     */
    public List<Task> selectFinishedTasks(String teacher_no);


    /**
     * 更新任务 （修改 is_finished 增加 finished_time）
     * @param task
     * @return
     */
    public int updateTask(Task task);

    /**
     * 找到 apl_no
     * @param task_no
     * @return
     */
    public int getApl_noByTask_no(int task_no);

    /**
     *
     * 查找 task 带有流程表
     */
    public Task selectTaskByNo(int task_no);

    /**
     * 获取教师名
     * @param task_no
     * @return
     */
    public String getTeacherNameByNo(int task_no);


    /**
     * 找出 task_nos
     * @param apl_no
     * @return
     */
    public List<Integer> getTaskNosByAplNo(int apl_no);

    /**
     * 批量修改 apl_no
     * @param alterAplAssist
     * @return
     */
    public int alterApl_nos(TaskAlterAplAssist alterAplAssist);

    /**
     * 批量中断任务
     */
    public int breakOffTask(Task task);
}
