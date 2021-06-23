package com.place_application.demo.utils;



import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {
    /**
     * 发送get请求
     * @param （get请求的url）
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
            httpGet.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 发送post请求
     * @param url
     * @param param
     * @return
     */
    public static JSONObject doPostStr(String url,String param){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(param, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(),"UTF-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject);
        return jsonObject;
    }


    public static void main(String[] args) {
        String url ="http://jwxt.gduf.edu.cn/app.do";
        String params = "method=authUser&xh=191543214&pwd=yya063514";
        JSONObject jsonObject = HttpUtil.doGetStr(url+"?"+params);
        System.out.println(jsonObject.get("userrealname"));
    }
}