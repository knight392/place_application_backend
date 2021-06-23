package com.place_application.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.place_application.demo.pojo.Position;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.PositionService;

import java.util.List;

/**
 * 职位后端控制器
 */
@Controller
public class PositionController {
    @Autowired
    private PositionService positionService;
    /**
     * 添加职位
     */
    @RequestMapping(value="/addPositionServlet",method = RequestMethod.POST)
    @ResponseBody
    public Response<Position> addPosition(@RequestBody Position position) {
        Response response = new Response();
        try{
            position = this.positionService.addPosition(position);
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("添加职位异常，可能场地名已被占用");
            response.setStatus(Response.ERROR);
            return response;
        }
        if (position != null){
            response.setStatus(Response.OK);
            response.setInfo("添加职位成功");
        }else{
            response.setStatus(Response.ERROR);
            response.setInfo("添加职位失败");
        }
        response.setData(position);
        return response;
    }

    /**
     * 删除职位
     */
    @RequestMapping(value="/deletePositionServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<Boolean> deletePosition(@RequestParam Integer position_no) {
        Response response = new Response();
        boolean res = false;
        try{
            res = this.positionService.deletePosition(position_no);
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("删除职位异常");
            return response;
        }
        if(res) {
            response.setStatus(Response.OK);
            response.setInfo("删除职位成功");
        }else{
            response.setStatus(Response.ERROR);
            response.setInfo("删除职位失败");
        }
        response.setData(res);
        return response;
    }

    /**
     * 修改职位
     */
    @RequestMapping(value="/updatePositionServlet", method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> updatePosition(@RequestBody Position position) {
        Response response = new Response();
        boolean res = false;
        try{
            res = this.positionService.updatePosition(position);
        }catch (Exception e){
            System.out.println(e);
            response.setStatus(Response.ERROR);
            response.setInfo("更新职位信息异常");
            return response;
        }
        if(res) {
            response.setStatus(Response.OK);
            response.setInfo("更新职位信息成功");
        }else{
            response.setStatus(Response.ERROR);
            response.setInfo("更新职位信息失败");
        }
        response.setData(res);
        return response;
    }

    @RequestMapping(value="/getAllPositionsServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Position>> getAllPositions() {
        Response response = new Response();
        List<Position> positions = null;
        try{
            positions = this.positionService.selectAllPositions();
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("获取所有职位异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if (positions != null){
            response.setInfo("获取所有职位成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("获取所有职位失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(positions);
        return response;
    }

    @RequestMapping(value="/sentencePositionDuplicateServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<Boolean> isPositionDuplicate(@RequestParam String position_name){
        Response response = new Response();
        boolean res = false;
        try{
            res = this.positionService.isPositionDuplicate(position_name);
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("判断职位是否重复异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if(res) {
            response.setInfo("职位名重复");
            response.setData(true);
        }else{
            response.setInfo("没有该职位名");
            response.setData(false);
        }
        response.setStatus(Response.OK);
        return response;
    }

    @RequestMapping(value="/getPositionsWithoutProcedureServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Position>> getPositionsWithoutProcedure() {
        Response response = new Response();
        List<Position> positions = null;
        try{
            positions = this.positionService.selectPositionsWithoutProcedure();
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("获取职位异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if (positions != null){
            response.setInfo("获取职位成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("获取职位失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(positions);
        return response;
    }


}
