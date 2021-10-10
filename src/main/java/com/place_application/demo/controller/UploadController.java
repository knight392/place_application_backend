package com.place_application.demo.controller;


import com.place_application.demo.pojo.Image;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;


/**
 * @Description
 * @Author sgl
 * @Date 2018-05-15 14:04
 */
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private ImageService imageService;

    @PostMapping("/uploadServlet")
    @ResponseBody
    public Response<Integer> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Response response = new Response();

        if (file.isEmpty()) {
            response.setStatus(Response.ERROR);
            response.setInfo("上传失败，请选择文件");
            return response;
        }

        //设置上传文件的保存地址目录
        String filePath = "C:/Users/小安/IdeaProjects/place_application/src/main/resources/static";
        String relativePath = "/images/upload/places/";
        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + relativePath);
        // 如果保存文件的地址不存在，就先创建目录
        if(!dest.exists()){
            System.out.println("创建");
            dest.mkdir();
        }

        try {
            file.transferTo(new File(filePath + relativePath + fileName));
            // 图片文件
            File fileImg = new File(filePath + relativePath + fileName);
            BufferedImage imageFile = ImageIO.read(fileImg);
            // 获取真实宽高
            int height = imageFile.getHeight();
            int width = imageFile.getWidth();

           // 图片对象保存数据库
            Image image = new Image();
            image.setPath(relativePath + fileName);
            image.setHeight(height);
            image.setWidth(width);

            image.setType("place");
            this.imageService.addImage(image);
            LOGGER.info("上传成功");
            response.setInfo("上传成功");
            response.setStatus(Response.OK);
            response.setData(image.getImg_no()); // 返回 img_no
            return response;
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        response.setStatus(Response.ERROR);
        response.setInfo("上传失败");
        return response;
    }
}


