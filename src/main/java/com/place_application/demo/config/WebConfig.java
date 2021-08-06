package com.place_application.demo.config;

import com.place_application.demo.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 管理员端拦截，只有登录了才能访问以下接口
        List<String> includePathLists= new ArrayList<>();

        includePathLists.add("/adminLoginWithTokenServlet");
        includePathLists.add("/adminUpdateServlet");

        includePathLists.add("/getAllAplProceduresServlet");
        includePathLists.add("/addAplProcedureServlet");
        includePathLists.add("/updateAplProcedureServlet");
        includePathLists.add("/deleteAplProcedureServlet");
        includePathLists.add("/sentenceAplProcedureDuplicateServlet");

        includePathLists.add("/addPlaceServlet");
        includePathLists.add("/deletePlaceServlet");
        includePathLists.add("/updatePlaceServlet");
        includePathLists.add("/getPlacesWithoutProcedureServlet");

        includePathLists.add("/addPositionServlet");
        includePathLists.add("/deletePositionServlet");
        includePathLists.add("/updatePositionServlet");
        includePathLists.add("/getAllPositionsServlet");
        includePathLists.add("/deletePositionServlet");
        includePathLists.add("/addPositionServlet");
        includePathLists.add("/sentencePositionDuplicateServlet");
        includePathLists.add("/getPositionsWithoutProcedureServlet");

        includePathLists.add("/sentenceTeacherDuplicateServlet");
        includePathLists.add("/registerTeacherServlet");
        includePathLists.add("/loginTeacherServlet");
        includePathLists.add("/deleteTeacherServlet");
        includePathLists.add("/getAllTeachersServlet");
        includePathLists.add("/updateTeacherServlet");

        registry.addInterceptor(new AdminInterceptor()).addPathPatterns(includePathLists);
    }
}
