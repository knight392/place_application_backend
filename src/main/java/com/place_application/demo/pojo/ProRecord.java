package com.place_application.demo.pojo;

/**
 * 审批过程记录
 */
public class ProRecord {
    private Integer apl_no; //申请表单号
    private String status_time; //状态生成时间
//    状态类型是数字,1~7数字表示不同状态
    private Integer status_type; //状态类型
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

    public Integer getStatus_type() {
        return status_type;
    }

    public void setStatus_type(Integer status_type) {
        this.status_type = status_type;
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
                ", status_type='" + status_type + '\'' +
                ", status_info='" + status_info + '\'' +
                '}';
    }
}
