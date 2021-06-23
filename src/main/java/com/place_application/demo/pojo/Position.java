package com.place_application.demo.pojo;

/**
 * 职位
 */
public class Position {
    private Integer position_no; // 职位编号
    private String position_name; // 职位名称
    private String position_info; // 职位描述
    private AplProcedure aplProcedure; // 所属流程
    private int pro_order; // 流程中的次序
    private Teacher teacher; // 教师编号

    public AplProcedure getAplProcedure() {
        return aplProcedure;
    }

    public void setAplProcedure(AplProcedure aplProcedure) {
        this.aplProcedure = aplProcedure;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public Integer getPosition_no() {
        return position_no;
    }

    public void setPosition_no(Integer position_no) {
        this.position_no = position_no;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPosition_info() {
        return position_info;
    }

    public void setPosition_info(String position_info) {
        this.position_info = position_info;
    }

    public int getPro_order() {
        return pro_order;
    }

    public void setPro_order(int pro_order) {
        this.pro_order = pro_order;
    }


    @Override
    public String toString() {
        return "position [position_no=" + position_no + ", position_name=" + position_name +
        ", position_info" + position_info + ", aplProcedure=" + aplProcedure + ", pro_order=" + pro_order + ", teacher=" + teacher + "]";
    }
}
