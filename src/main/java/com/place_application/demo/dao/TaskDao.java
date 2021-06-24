package com.place_application.demo.dao;


import com.place_application.demo.pojo.Task;

/**
 * 任务审批持久层操作
 */
public interface TaskDao {
    /**
     * 添加审批任务
     */
    public void insertTask(Task task);
    /**
     * 查看审批任务
     */
    public Task selectTask(Integer task_no);
}
