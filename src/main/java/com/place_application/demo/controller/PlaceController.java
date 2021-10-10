package com.place_application.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.place_application.demo.pojo.Place;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.PlaceService;

import java.util.List;

/**
 * 场地后端控制器
 */
@Controller
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    /**
     * 新增场地
     */
    @RequestMapping(value = "/addPlaceServlet", method = RequestMethod.POST)
    @ResponseBody
    public Response<Place> addPlace(@RequestBody Place place) {
        Response response = new Response();
        try{
           place = this.placeService.addPlace(place);
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("添加场地异常，可能场地名已被占用");
            response.setStatus(Response.ERROR);
            return response;
        }
        if(place != null){
            response.setInfo("添加场地成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("添加场地失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(place);
        return response;
    }

    /**
     * 删除场地
     */
    @RequestMapping(value = "/deletePlaceServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<Boolean> deletePlace(@RequestParam Integer place_no) {
        Response response = new Response();
        boolean res = false;
        try{
           res = this.placeService.deletePlace(place_no);
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("删除场地异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if(res){
            response.setInfo("删除场地成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("删除场地失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(res);
        return response;
    }

    /**
     * 查看获取所有场地
     */
    @RequestMapping(value = "/getAllPlacesServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Place>> getAllPlaces() {
        Response response = new Response();
        List<Place> places = null;
        try{
            places = this.placeService.selectAllPlaces();
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("获取所有场地异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if (places != null){
            response.setInfo("获取所有场地成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("获取所有场地失败");
            response.setStatus(Response.ERROR);
        }

        response.setData(places);

        return response;
    }

    /**
     * 修改场地信息
     */
    @RequestMapping(value = "/updatePlaceServlet", method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> updatePlace(@RequestBody Place place) {
        Response response = new Response();
        boolean res = false;
        try {
            res = this.placeService.updatePlace(place);
        }catch (Exception e) {
            e.printStackTrace();
            response.setInfo("更新场地信息异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if (res){
            response.setInfo("更新场地信息成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("更新场地信息失败");
            response.setStatus(Response.ERROR);
        }
        response.setData(res);
        return response;
    }

    /**
     * 判断场地是否重复
     */
    @RequestMapping(value = "/sentencePlaceDuplicateServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<Boolean> isPlaceDuplicate(@RequestParam String place_name) {
        Response response = new Response();
        boolean res = false;
        try {
            res = this.placeService.isPlaceDuplicate(place_name);
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("判断场地名是否重复异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if(res) {
            response.setInfo("场地名重复");
        }else{
            response.setInfo("还没有该场地名");
        }
        response.setData(res);
        response.setStatus(Response.OK);
        return response;
    }

    /**
     * 查看流程为空的场地
     */
    @RequestMapping(value = "/getPlacesWithoutProcedureServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Place>> getPlacesWithoutProcedure() {
        Response response = new Response();
        List<Place> places = null;
        try{
            places = this.placeService.getPlacesWithoutProcedure();
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("获取场地异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if (places != null){
            response.setInfo("获取场地成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("获取场地失败");
            response.setStatus(Response.ERROR);
        }

        response.setData(places);
        return response;
    }

    /**
     * 查序可以被申请的场地
     * available 为 1
     */
    @RequestMapping(value = "/getPlacesAvailableServlet",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Place>> getPlacesAvailable() {
        Response response = new Response();
        List<Place> places = null;
        try{
            places = this.placeService.getPlacesAvailable();
        }catch (Exception e) {
            System.out.println(e);
            response.setInfo("获取场地异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if (places != null){
            response.setInfo("获取场地成功");
            response.setStatus(Response.OK);
        }
        else{
            response.setInfo("获取场地失败");
            response.setStatus(Response.ERROR);
        }

        response.setData(places);
        return response;
    }

}
