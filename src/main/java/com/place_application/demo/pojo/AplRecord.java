package com.place_application.demo.pojo;

/**
 * 审批过程记录
 */
public class AplRecord {
    private Integer apl_no; //申请表单号
    private String status_time; //状态生成时间

    private String status_info; //状态描述

    public Integer getApl_no() {
        return apl_no;
    }

    public void setApl_no(Integer apl_no) {
        this.apl_no = apl_no;
    }

    public String getStatus_time() {
        return status_time;
    }

    public void setStatus_time(String status_time) {
        this.status_time = status_time;
    }



    public String getStatus_info() {
        return status_info;
    }

    public void setStatus_info(String status_info) {
        this.status_info = status_info;
    }

    @Override
    public String toString() {
        return "ProRecord{" +
                "apl_no=" + apl_no +
                ", status_time='" + status_time + '\'' +
                ", status_info='" + status_info + '\'' +
                '}';
    }
}
