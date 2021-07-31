package com.place_application.demo.controller;

import com.place_application.demo.config.JwtConfig;
import com.place_application.demo.utils.HttpUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.place_application.demo.pojo.Admin;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.AdminService;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员后端控制器
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Resource
    private JwtConfig jwtConfig ;

    /**
     * 使用token登录
     */
    @RequestMapping(value = "/adminLoginWithTokenServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<Admin> AdminLoginWithToken(@CookieValue(value = "token") String token) throws Exception {
        if(token == null || token == "") {
            throw new SignatureException(jwtConfig.getHeader()+ "不能为空");
        }
        Admin adminRes = this.adminService.loginAdminWithToken(token);
        Response response = new Response();
        if(adminRes == null){
            response.setInfo("该用户已移除");
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
     * 登录请求
     */
    @RequestMapping(value = "/adminLoginServlet", method = RequestMethod.POST)
    @ResponseBody
    public Response<Admin> adminLogin(@RequestBody Admin admin, HttpServletResponse res) {
        Admin adminRes = null;
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
            // 设置token
            String token = jwtConfig.createToken(admin.getAdmin_no()) ;
            System.out.println(token);
            Cookie cookie = new Cookie("token",token);
            res.addCookie(cookie);
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
