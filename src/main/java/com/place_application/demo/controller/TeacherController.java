package com.place_application.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.pojo.Teacher;
import com.place_application.demo.service.TeacherService;

import java.util.List;

/**
 * 教师后端控制器
 */
@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    /**
     * 判断教师账号名是否重复
     */
    @RequestMapping(value="/sentenceTeacherDuplicateServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<Boolean> isTeacherDuplicate(@RequestParam String teacher_no){
        Response response = new Response();
        boolean res = false;
        try {
            res = this.teacherService.isTeacherDuplicate(teacher_no);
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("判断教师账号是否重复异常");
            return response;
        }
        if(res){
            response.setInfo("教师账号重复");
        }else{
            response.setInfo("没有该教师账号");
        }
        response.setStatus(Response.OK);
        response.setData(res);
        return response;
    }
    /**
     * 注册教师账号
     */
    @RequestMapping(value = "/registerTeacherServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Teacher> registerTeacher(@RequestBody Teacher teacher){
        Response response = new Response();
        try{
           teacher =  this.teacherService.registerTeacher(teacher);
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("教师账号注册异常");
            return response;
        }
        if(teacher == null){
            response.setInfo("教师账号注册失败");
            response.setStatus(Response.ERROR);
        }else{
            response.setInfo("教师账号注册成功");
            response.setStatus(Response.OK);
        }
        response.setData(teacher);
        return response;
    }

    /**
     * 登录教师账号
     */
    @RequestMapping(value = "/loginTeacherServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Teacher> loginTeacher(@RequestBody Teacher teacher){
        Response response = new Response();
        try{
            teacher = this.teacherService.loginTeacher(teacher);
        }catch (Exception e){
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("教师账号登录异常");
            return response;
        }
        if(teacher == null){
            response.setInfo("教师账号登录失败");
            response.setStatus(Response.ERROR);
        }else{
            response.setInfo("教师账号登录成功");
            response.setStatus(Response.OK);
        }
        response.setData(teacher);
        return response;
    }


    /**
     * 删除教师账号
     */
    @RequestMapping(value = "/deleteTeacherServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<Boolean> deleteTeacher(@RequestParam String teacher_no){
        Response response = new Response();
        boolean res = false;
        try{
            res = this.teacherService.deleteTeacher(teacher_no);
        }catch (Exception e){
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("教师账号删除异常");
            return response;
        }
        if(! res){
            response.setInfo("教师账号删除失败");
            response.setStatus(Response.ERROR);
        }else{
            response.setInfo("教师账号删除成功");
            response.setStatus(Response.OK);
        }
        response.setData(res);
        return response;
    }

    /**
     * 获取所有的教师
     */
    @RequestMapping(value = "/getAllTeachersServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Teacher>> getAllTeachers() {
        Response response = new Response();
        List<Teacher> teacherList = null;
        try{
            teacherList = this.teacherService.getAllTeachers();
        }catch (Exception e){
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("获取教师信息异常");
            return response;
        }
        if(teacherList == null){
            response.setInfo("获取教师信息失败");
            response.setStatus(Response.ERROR);
        }else{
            response.setInfo("获取教师信息成功");
            response.setStatus(Response.OK);
        }
        response.setData(teacherList);
        return response;
    }

    /**
     * 修改教师信息
     */
    @RequestMapping(value = "/updateTeacherServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> updateTeacher(@RequestBody Teacher teacher) {
        Response response = new Response();
        boolean res = false;
        try{
            res = this.teacherService.updateTeacher(teacher);
        }catch (Exception e){
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("教师信息更新异常");
            return response;
        }
        if(! res){
            response.setInfo("教师信息更新失败");
            response.setStatus(Response.ERROR);
        }else{
            response.setInfo("教师信息更新成功");
            response.setStatus(Response.OK);
        }
        response.setData(res);
        return response;
    }

}
