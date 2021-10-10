package com.place_application.demo.controller;

import com.place_application.demo.pojo.AplRecord;
import com.place_application.demo.pojo.Response;
import com.place_application.demo.service.AplRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AplRecordController {
    @Autowired
    private AplRecordService aplRecordService;
    /**
     * 获取 申请表的 申请记录
     * @param apl_no
     * @return
     */
    @RequestMapping(value = "/getAplRecordsServlet", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<AplRecord>> getAplRecords(@RequestParam int apl_no){
        Response response = new Response();
        List<AplRecord> aplRecordList = null;
        try{
            aplRecordList = this.aplRecordService.getAplRecords(apl_no);
        }catch (Exception e){
            response.setInfo("获取记录异常");
            response.setStatus(Response.ERROR);
            return response;
        }
        if(aplRecordList == null){
            response.setInfo("获取记录失败");
            response.setStatus(Response.ERROR);
            return response;
        }
        response.setInfo("获取记录成功");
        response.setStatus(Response.OK);
        response.setData(aplRecordList);
        return response;
    }
}
