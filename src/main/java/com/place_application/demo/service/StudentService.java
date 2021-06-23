package com.place_application.demo.service;

import com.place_application.demo.pojo.Student;
import org.springframework.stereotype.Service;

/**
 * 学生服务
 */
@Service
public interface StudentService {

    /**
     * 学生登录
     */
    public Student loginStudent(Student student) throws Exception;

    /**
     * 新增学生
     */
    public boolean registerStudent(Student student);

    /**
     * 查询学生信息
     */
    public Student selectStudent(String s_no);


}
