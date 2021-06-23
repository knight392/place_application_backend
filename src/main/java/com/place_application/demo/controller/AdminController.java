package com.place_application.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.place_application.demo.pojo.Admin;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.AdminService;

/**
 * 管理员后端控制器
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    /**
     * 登录请求
     */
    @RequestMapping(value = "/adminLoginServlet", method = RequestMethod.POST)
    @ResponseBody
    public Response<Admin> adminLogin(@RequestBody Admin admin) {
        Admin adminRes = null;
        System.out.println(admin);
        Response response = new Response();
        try{
            adminRes = this.adminService.loginAdmin(admin);
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("登录异常");
            response.setData(Response.ERROR);
            return response;
        }

        if(adminRes == null){
            response.setInfo("账号或密码有误");
            response.setStatus(Response.ERROR);
        }
        else{
            response.setInfo("登录成功");
            response.setStatus(Response.OK);
        }

        response.setData(adminRes);
        return response;
    }

    /**
     * 修改管理员信息
     */
    @RequestMapping(value="/adminUpdateServlet", method = RequestMethod.POST)
    @ResponseBody
    public Response<Admin> adminUpdate(@RequestBody Admin admin) {
        Response response = new Response();
        try{
           admin = this.adminService.updateAdmin(admin);
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("管理员信息修改异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if(admin != null){
            response.setInfo("管理员信息修改成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("管理员信息修改失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(admin);
        return response;
    }
}
