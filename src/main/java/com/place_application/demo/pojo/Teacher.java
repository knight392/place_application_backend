package com.place_application.demo.pojo;

import java.util.List;

/**
 * 教师
 */
public class Teacher {

    private String teacher_no; // 账号
    private String teacher_name; // 名称
    private String teacher_password; // 密码
    private List<Position> positions; // 拥有的职位

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public String getTeacher_no() {
        return teacher_no;
    }

    public void setTeacher_no(String teacher_no) {
        this.teacher_no = teacher_no;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public void setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
    }

    @Override
    public String toString() {
        return "teacher [" + teacher_no + ", teacher_name=" + teacher_name + ", teacher_password = " + teacher_password + ", positions=" + positions + "]";
    }
}
