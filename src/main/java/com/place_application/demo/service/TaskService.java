package com.place_application.demo.service;

import com.place_application.demo.pojo.PlaceApplication;
import com.place_application.demo.pojo.SentenceRequest;
import com.place_application.demo.pojo.Task;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {

    /**
     * 添加任务
     * @param now
     * @param placeApplication
     * @param teacher_no
     * @return
     */
    public int addTask(String now, PlaceApplication placeApplication, String teacher_no, int is_reSentence);


    /**
     * 5个一组，查看第 k 组的待审批任务
     * @param teacher_no
     * @param task_no
     * @return
     */
    public List<Task> getTodoTasks(String teacher_no, Integer task_no);

    /**
     * 5个一组，查看第 k 组的已审批任务
     * @param teacher_no
     * @param task_no
     * @return
     */
    public List<Task> getFinishedTasks(String teacher_no, Integer task_no);

    /**
     * 审批申请
     * @param sentenceRequest
     * @return
     */
    public Boolean sentenceTask(SentenceRequest sentenceRequest) throws Exception;

    /**
     * 中断任务
     * @param aplList
     * @return
     */
    public Boolean breakOffTasks(List<Integer> aplList);

}
