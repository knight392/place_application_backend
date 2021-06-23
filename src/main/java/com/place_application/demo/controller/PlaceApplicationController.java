package com.place_application.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.place_application.demo.pojo.PlaceApplication;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.PlaceApplicationService;

import java.util.List;

/**
 * 场地申请表后端控制器
 */
@Controller
public class PlaceApplicationController {
    @Autowired
    private PlaceApplicationService placeApplicationService;
    /**
     * 提交申请表
     */
    @RequestMapping(value = "/submitApplicationServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> submitApplication(@RequestBody PlaceApplication placeApplication){
        Response response = new Response();
        boolean res = false;
        try {
            res = this.placeApplicationService.addPlaceApplication(placeApplication);
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("提交申请表异常");
            return response;
        }
        if(res) {
            response.setStatus(Response.OK);
            response.setInfo("提交申请表成功");
        }else{
            response.setStatus(Response.ERROR);
            response.setInfo("提交申请表失败");
        }
        response.setData(res);
        return response;
    }

    /**
     * 查看申请表
     */



    /**
     * 补正申请表
     */
    @RequestMapping(value = "/updateApplicationServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> updateApplication(@RequestBody PlaceApplication placeApplication) {
        Response response = new Response();
        boolean res = false;
        try {
            res = this.placeApplicationService.updatePlaceApplication(placeApplication);
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("补正申请表异常");
            return response;
        }
        if(res) {
            response.setStatus(Response.OK);
            response.setInfo("补正申请表成功");
        }else{
            response.setStatus(Response.ERROR);
            response.setInfo("补正申请表失败");
        }
        response.setData(res);
        return response;
    }


    /**
     * 根据学号查询所有的申请表
     */
    @RequestMapping(value = "/getApplicationsServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<PlaceApplication>> getApplicationsByStudent_no(@RequestParam String s_no) {
        Response response = new Response();
        List<PlaceApplication> placeApplications = null;
        try {
            placeApplications = this.placeApplicationService.getApplicationsByStudent_no(s_no);
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("获取所有学生所属申请表异常");
            return response;
        }
        if(placeApplications != null) {
            response.setStatus(Response.OK);
            response.setInfo("获取所有学生所属申请表成功");
        }else{
            response.setStatus(Response.ERROR);
            response.setInfo("获取所有学生所属申请表失败");
        }
        response.setData(placeApplications);
        return response;
    }
}
