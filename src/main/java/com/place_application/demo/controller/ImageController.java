package com.place_application.demo.controller;

import com.place_application.demo.pojo.Image;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    /**
     * 获取学生端swiper的图片路径
     */
    @RequestMapping(value = "/stuSwiperServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Image>> getStuSwiperImage(){
        Response response = new Response();
        List<Image> images = null;
        try{
            images = this.imageService.getImagesByType("stu_swiper");
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("获取图片异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if (images != null){
            response.setInfo("获取图片成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("获取图片失败");
            response.setStatus(Response.ERROR);
        }

        response.setData(images);
        return response;

    }

}
