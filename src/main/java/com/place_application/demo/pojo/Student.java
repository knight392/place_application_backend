package com.place_application.demo.pojo;

/**
 * 学生类
 */
public class Student {

    private String s_no; // 学号
    private String s_name; // 姓名
    private String s_password; // 密码
    private String s_dwmc; // 学院
    private String s_avatar; // 头像
    private String s_phone; // 手机号码

    public String getS_avatar() {
        return s_avatar;
    }

    public void setS_avatar(String s_avatar) {
        this.s_avatar = s_avatar;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }



    public String getS_no() {
        return s_no;
    }

    public void setS_no(String s_no) {
        this.s_no = s_no;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_dwmc() {
        return s_dwmc;
    }

    public void setS_dwmc(String s_dwmc) {
        this.s_dwmc = s_dwmc;
    }

    @Override
    public String toString() {
        return "Student{" +
                "s_no='" + s_no + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_password='" + s_password + '\'' +
                ", s_dwmc='" + s_dwmc + '\'' +
                ", s_avatar='" + s_avatar + '\'' +
                ", s_phone='" + s_phone + '\'' +
                '}';
    }
}
