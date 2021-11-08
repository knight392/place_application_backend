package com.place_application.demo.controller;

import com.place_application.demo.pojo.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping(value = "/testServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<Integer> test(@RequestParam int no){
        Response<Integer> response = new Response<>();
        response.setStatus(Response.OK);
        response.setInfo("请求响应成功");
        response.setData(no);
        return response;

    }
}
