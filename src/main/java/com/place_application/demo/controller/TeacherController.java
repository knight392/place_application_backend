package com.place_application.demo.controller;

import com.place_application.demo.config.JwtConfig;
import com.place_application.demo.pojo.Admin;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.pojo.Teacher;
import com.place_application.demo.service.TeacherService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 教师后端控制器
 */
@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Resource
    private JwtConfig jwtConfig ;
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
    public Response<Teacher> loginTeacher(@RequestBody Teacher teacher, HttpServletResponse res){
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
            // 设置 token
            String token = jwtConfig.createToken(teacher.getTeacher_no()) ;
            System.out.println(token);
            Cookie cookie = new Cookie("token", token);
            res.addCookie(cookie);
            response.setInfo("登录成功");
            response.setStatus(Response.OK);
        }
        response.setData(teacher);
        return response;
    }


    /**
     * 教师使用 token 登录
     * @param token
     * @return
     */
    @RequestMapping(value = "/loginTeacherWithTokenServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<Teacher> loginTeacherWithToken(@CookieValue(value = "token") String token){
        if(token == null || token == "") {
            throw new SignatureException(jwtConfig.getHeader()+ "不能为空");
        }
        Response response = new Response();
        Teacher teacher = this.teacherService.loginTeacherWithToken(token);
        if(teacher == null){
            response.setInfo("登录异常");
            response.setStatus(Response.ERROR);
        }
        else{
            response.setInfo("登录成功");
            response.setStatus(Response.OK);
        }
        response.setData(teacher);
        return response;
    }


    /**
     * 教师退出登录
     * @param res
     * @return
     */
    @RequestMapping(value = "logoffTeacherServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<Boolean> logoffTeacher(HttpServletResponse res){
        Response response = new Response();
        Cookie cookie = new Cookie("token","");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        response.setInfo("退出登录成功");
        response.setData(true);
        response.setStatus(200);
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


    /**
     * 找到流程所安排的所有审批老师
     */
    @RequestMapping(value = "/getTeachersInProcedure",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Teacher>> getTeachersInProcedure(@RequestParam String pro_no){
        Response response = new Response();
        List<Teacher> teacherList = null;
        try{
            teacherList = this.teacherService.getTeachersInProcedure(pro_no);
        }catch (Exception e){
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("教师信息获取异常");
            return response;
        }
        if(teacherList.size() == 0){
            response.setInfo("没有该流程或该流程没有教师");
            response.setStatus(Response.ERROR);
        }else{
            response.setInfo("获取教师信息成功");
            response.setStatus(Response.OK);
        }
        response.setData(teacherList);
        return response;

    }

}
