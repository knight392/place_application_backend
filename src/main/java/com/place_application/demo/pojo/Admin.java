package com.place_application.demo.pojo;

/**
 * 管理员
 */
public class Admin {
    private String admin_no;    // 账号
    private String admin_name;  // 名称
    private String admin_password; // 密码

    public String getAdmin_no() {
        return admin_no;
    }

    public void setAdmin_no(String admin_no) {
        this.admin_no = admin_no;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public String toString() {
        return "admin[admin_no" + admin_no + ", admin_name = " + admin_name + ", admin_password=" + admin_password + "]";
    }
}
