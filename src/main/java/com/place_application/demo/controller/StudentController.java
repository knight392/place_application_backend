package com.place_application.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.pojo.Student;
import com.place_application.demo.service.StudentService;

/**
 * 学生后端控制器
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 学生登录
     */
    @RequestMapping(value = "/studentLoginServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Student> loginStudent(@RequestBody Student student){
        Response response = new Response();
        try{
            student = this.studentService.loginStudent(student);
        }catch(Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("学生登录异常");
            return response;
        }
        if (student != null) {
            response.setStatus(Response.OK);
            response.setInfo("学生登录成功");
        }else{
            response.setStatus(Response.ERROR);
            response.setInfo("学生登录失败");
        }
        response.setData(student);
        return response;
    }

    /**
     * 修改学生信息 头像或手机号码
     */
    @RequestMapping(value = "/studentUpdateServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> updateStudent(@RequestBody Student student){
        Response response = new Response();
        Boolean res = false;
        student.toString();
        System.out.println("更新");
        try{
            res = this.studentService.updateStudent(student);
        }catch(Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("学生信息更新异常");
            return response;
        }
        if (res) {
            response.setStatus(Response.OK);
            response.setInfo("学生信息修改成功");
        }else{
            response.setStatus(Response.ERROR);
            response.setInfo("学生信息修改失败");
        }
        response.setData(res);
        return response;
    }
}
