package com.place_application.demo.controller;

import com.place_application.demo.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.place_application.demo.pojo.AplProcedure;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.AplProcedureService;

import java.util.List;

/**
 * 申请流程后端控制器
 */
@Controller
public class AplProcedureController {
    @Autowired
    private AplProcedureService aplProcedureService;
    /**
     *  获取所有流程
     */
    @RequestMapping(value = "/getAllAplProceduresServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<AplProcedure>> getAllAplProcedures() {
        Response response = new Response();
        List<AplProcedure> aplProcedureList = null;
        try{
           aplProcedureList =  this.aplProcedureService.selectAllAplProcedures();
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("获取所有的流程异常");
            return response;
        }
        if (aplProcedureList != null) {
            response.setInfo("获取所有流程成功");
            response.setStatus(Response.OK);
        }else {
            response.setInfo("获取所有流程失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(aplProcedureList);
        return response;
    }


    /**
     * 添加流程
     */
    @RequestMapping(value = "/addAplProcedureServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<AplProcedure> addAplProcedure(@RequestBody AplProcedure aplProcedure) {
        Response response = new Response();
        try{
            aplProcedure = this.aplProcedureService.addAplProcedure(aplProcedure);
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("添加流程异常");
            return response;
        }
        if (aplProcedure != null) {
            response.setInfo("添加流程成功");
            response.setStatus(Response.OK);
        }else {
            response.setInfo("添加流程失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(aplProcedure);
        return response;
    }


    /**
     * 修改流程
     */
    @RequestMapping(value = "/updateAplProcedureServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> updateAplProcedure(@RequestBody AplProcedure aplProcedure){
        Response response = new Response();
        System.out.println(aplProcedure);
        boolean res = false;
        try {
            res = this.aplProcedureService.updateAplProcedure(aplProcedure);
        }catch(Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("更新流程异常");
            return response;
        }
        if (res) {
            response.setInfo("更新流程成功");
            response.setStatus(Response.OK);
        }else {
            response.setInfo("更新流程失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(res);
        return response;
    }


    /**
     * 删除流程
     */
    @RequestMapping(value = "/deleteAplProcedureServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<Boolean> deleteProcedure(@RequestParam Integer pro_no){
        Response response = new Response();
        boolean res = false;
        try {
            res = this.aplProcedureService.deleteAplProcedure(pro_no);
        }catch(Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("删除流程异常");
            return response;
        }
        if (res) {
            response.setInfo("删除流程成功");
            response.setStatus(Response.OK);
        }else {
            response.setInfo("删除流程失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(res);
        return response;
    }

    /**
     * 判断流程名称是否重复
     * @param aplProcedure
     * @return
     */
    @RequestMapping(value = "/sentenceAplProcedureDuplicateServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> sentenceAplProcedure(@RequestBody AplProcedure aplProcedure){
        Response response = new Response();
        boolean res = false;
        try {
            res = this.aplProcedureService.isAplProcedureDuplicate(aplProcedure);
        }catch(Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("判断流程是否重复异常");
            return response;
        }
        if (res) {
            response.setInfo("该流程名或对应申请表名重复");
        }else {
            response.setInfo("该流程名和申请表名都可用");
        }
        response.setStatus(Response.OK);
        response.setData(res);
        return response;
    }


}