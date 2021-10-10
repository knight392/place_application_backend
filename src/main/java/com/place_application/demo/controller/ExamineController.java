package com.place_application.demo.controller;

import com.place_application.demo.pojo.Response;
import com.place_application.demo.pojo.SentenceRequest;
import com.place_application.demo.pojo.Task;
import com.place_application.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

/**
 * 审批控制器
 */
@Controller
public class ExamineController {
    @Autowired
    private TaskService taskService;

    /**
     * 根据教师账号获取从 任务生成时间 对应记录的下标 开始的后至多5个审批任务
     * @param teacher_no 教师账号
     * @param task_no 前端数据的最后一条任务的任务编号
     * @return
     */
    @RequestMapping(value = "/getTodoTasksServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Task>> getTodoTasks(@RequestParam String teacher_no, @RequestParam Integer task_no){
        Response response = new Response();
        List<Task> taskList = null;
        try{
            taskList = this.taskService.getTodoTasks(teacher_no, task_no);
        }catch (Exception e){
            response.setInfo("获取待审批任务异常");
            response.setStatus(Response.ERROR);
            return  response;
        }
        if(taskList == null || taskList.size() == 0){
            response.setInfo("没有更多待审批任务");
            response.setStatus(Response.OK);
            return  response;
        }
        response.setData(taskList);
        response.setInfo("获取待审批任务成功");
        response.setStatus(Response.OK);
        return response;
    }

    /**
     * 根据教师账号获取5条审批任务
     * @param teacher_no 教师账号
     * @param task_no 前端数据的最后一条任务的任务编号
     * @return
     */
    @RequestMapping(value = "/getFinishedTasksServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Task>> getFinishedTasks(@RequestParam String teacher_no, @RequestParam int task_no){
        Response response = new Response();
        List<Task> taskList = null;
        try{
            taskList = this.taskService.getFinishedTasks(teacher_no, task_no);
        }catch (Exception e){
            response.setInfo("获取已审批任务异常");
            response.setStatus(Response.ERROR);
            return  response;
        }
        if(taskList == null || taskList.size() == 0){
            response.setInfo("没有更多已审批任务");
            response.setStatus(Response.OK);
            return  response;
        }
        response.setData(taskList);
        response.setInfo("获取已审批任务成功");
        response.setStatus(Response.OK);
        return response;
    }

    /**
     * 教师提交审批结果
     * @param sentenceRequest
     * @return
     */
    @RequestMapping(value = "/sentenceTaskServlet", method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> sentenceTask(@RequestBody SentenceRequest sentenceRequest){
        Response response = new Response();
        boolean res = false;
        try{
           res = this.taskService.sentenceTask(sentenceRequest);
        }catch (Exception e){
            response.setInfo("审批异常");
            response.setStatus(Response.ERROR);
            e.printStackTrace();
            return response;
        }
        response.setData(res);
        if(!res){
            response.setInfo("审批失败");
            response.setStatus(Response.ERROR);
        }else{
            response.setInfo("审批成功");
            response.setStatus(Response.OK);
        }
        return response;
    }
}
